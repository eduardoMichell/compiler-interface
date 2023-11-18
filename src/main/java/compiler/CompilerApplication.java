package compiler;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import parser.Instruction;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class CompilerApplication extends Application {

    FileChooser fileChooser = new FileChooser();
    private static CompilerApplication instance;

    public static CompilerApplication getInstance() {
        return instance;
    }

    private File lastDirectory;
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(CompilerApplication.class.getResource("compiler-interface-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1800, 850);

        stage.setMaximized(true);
        stage.setMinWidth(550);
        stage.setMinHeight(690);

        stage.setTitle("Compilador");
        stage.setScene(scene);
        stage.show();
    }


    public void setLineAndColumn(TextArea codeTextArea, Text lineColumnText) {
        codeTextArea.caretPositionProperty().addListener((observable, oldValue, newValue) -> {
            int caretPosition = newValue.intValue();
            if (caretPosition > 0) {

                String text = codeTextArea.getText();
                int currentLine = 1;
                int currentColumn = 1;

                if (caretPosition <= text.length()) {
                    int lineStart = text.lastIndexOf('\n', caretPosition - 1) + 1;
                    currentColumn = caretPosition - lineStart + 1;
                    for (int i = 0; i < caretPosition; i++) {
                        if (text.charAt(i) == '\n') {
                            currentLine++;
                        }
                    }
                }
                lineColumnText.setText("Ln " + currentLine + ", Col " + currentColumn);
            } else {
                lineColumnText.setText("Ln " + 1 + ", Col " + 1);
            }
        });
    }

    public void updateTitle(String title) {
        if (Objects.equals(title, "")) {
            stage.setTitle("Compilador");
        } else {
            stage.setTitle("Compilador - " + title);
        }
    }

    public void defineSplitPane(TextArea codeTextArea, TextArea consoleTextArea, SplitPane splitPane) {
        VBox.setVgrow(codeTextArea, Priority.ALWAYS);
        HBox.setHgrow(consoleTextArea, Priority.ALWAYS);
        splitPane.getItems().remove(0);
        splitPane.getItems().remove(0);
        splitPane.getItems().addAll(codeTextArea, consoleTextArea);
        splitPane.setDividerPosition(0, 1);
    }

    public void autoResizeSplitPaneWidth(SplitPane splitPane, Text lineColumnText) {
        stage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double newTextAreaWidth = stage.getWidth() - 38;
            splitPane.setPrefWidth(newTextAreaWidth);
            lineColumnText.setLayoutX(10);
        });
    }

    public void autoResizeSplitPaneHeight(SplitPane splitPane, Text lineColumnText) {
        stage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            splitPane.setPrefHeight(stage.getHeight() - 160);
            lineColumnText.setLayoutY(stage.getHeight() - 75);
        });
    }

    public File openFile(String title) {
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        if (lastDirectory != null) {
            fileChooser.setInitialDirectory(lastDirectory);
        }
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            lastDirectory = file.getParentFile();
        }
        return file;
    }

    public File chooseFilePath(String title, String fileName) {
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        fileChooser.setInitialFileName(!fileName.isEmpty() ? fileName : "");
        return fileChooser.showSaveDialog(stage);
    }

    public void saveFile(String path, String code, CodeController controller, CompilerController compilerController) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save File");
            alert.setHeaderText("Do you want to save " + path);
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(yesButton, noButton, cancelButton);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == yesButton) {
                if (controller.getFile() != null) {
                    controller.saveFile(code, controller.getFile());
                    this.updateTitle(controller.getFileName());
                } else {
                    File newFile = chooseFilePath("Save New File", controller.getFileName());
                    if (lastDirectory != null) {
                        fileChooser.setInitialDirectory(lastDirectory);
                    }
                    if (newFile != null) {
                        lastDirectory = newFile.getParentFile();
                        controller.saveFile(code, newFile);
                        this.updateTitle(newFile.getName());
                    }
                }
            } else if (result.get() == noButton) {
                if (controller.isFileEdited()) {
                    compilerController.cancelSaveFile();
                }
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openTable(List<Instruction> instructions) {
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        TableView<Instruction> table =  new TableView();

        TableColumn<Instruction, Integer> pointerColumn = new TableColumn<>("Pointer");
        pointerColumn.setCellValueFactory(new PropertyValueFactory<>("pointer"));

        TableColumn<Instruction, String> codeColumn = new TableColumn<>("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Instruction, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        table.getColumns().addAll(pointerColumn, codeColumn, addressColumn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        for (Instruction inst : instructions){
            table.getItems().add(inst);
        }

        Scene tableScene = new Scene(table, 300, 220);
        newStage.setScene(tableScene);
        newStage.setTitle("Object Code");
        newStage.show();
    }



    public static void main(String[] args) {
        launch();
    }

}
