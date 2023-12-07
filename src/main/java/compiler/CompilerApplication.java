package compiler;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import parser.Instruction;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class CompilerApplication extends Application {
    FileChooser fileChooser = new FileChooser();
    private static CompilerApplication instance;
    private File lastDirectory;
    private Stage compilerStage;
    private String lastInput;
    TextArea runtime = new TextArea();
    TextField inputRuntime;
    Stage tableStage;
    Stage runtimeStage;
    private CompletableFuture<String> inputFuture;

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        this.compilerStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(CompilerApplication.class.getResource("compiler-interface-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1800, 850);
        stage.setMaximized(true);
        stage.setMinWidth(550);
        stage.setMinHeight(690);
        stage.setTitle("Compiler");
        stage.setScene(scene);
        stage.show();
    }

    public static CompilerApplication getInstance() {
        return instance;
    }

    public void openRuntime(CompilerController controller) {
        this.runtimeStage = new Stage();

        this.runtimeStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                controller.disableStopButton();
            }
        });

        VBox vBox = new VBox();
        runtime.setEditable(false);
        VBox.setVgrow(runtime, Priority.ALWAYS);

        inputRuntime = new TextField();
        inputRuntime.setDisable(false);
        inputRuntime.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                inputFuture.complete(inputRuntime.getText());
            }
        });

        inputRuntime.setPrefHeight(60);
        vBox.getChildren().addAll(runtime, inputRuntime);
        Scene runtimeScene = new Scene(vBox, 300, 220);
        this.runtimeStage.setScene(runtimeScene);
        this.runtimeStage.setTitle("Runtime");
        this.runtimeStage.show();
    }


    public void programStopped(){
        inputFuture = new CompletableFuture<>();
        inputFuture.complete("");
        inputRuntime.clear();
        inputRuntime.setDisable(true);
    }
    public void eraseInputRuntime() {
        inputRuntime.clear();
    }

    private CompletableFuture<String> readInputAsync() {
        inputFuture = new CompletableFuture<>();
        Platform.runLater(() -> {
            inputRuntime.requestFocus();
        });
        return inputFuture;
    }

    public CompletableFuture<String> getInputAsync() {
        return readInputAsync();
    }

    public String getLastInput() {
        return lastInput;
    }

    public void setLastInput(String lastInput) {
        this.lastInput = lastInput;
    }

    public void appendToRuntimeTerminal(String text) {
        runtime.appendText(text + "\n");
    }

    public void eraseRuntimeInput() {
        runtime.setText("");
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

    public void clearRuntime() {
        if(this.inputRuntime != null  && this.runtime != null) {
            this.inputRuntime.setText("");
            this.runtime.setText("");
        }
    }

    public void stop() {
        this.closeRuntimeStage();
        this.clearRuntime();
    }

    public void closeAll(){
        this.closeRuntimeStage();
        this.closeTableStage();
        this.clearRuntime();
    }

    public void closeRuntimeStage() {
        if (this.runtimeStage != null) {
            this.runtimeStage.close();
        }
    }

    public void closeTableStage() {
        if (this.tableStage != null) {
            this.tableStage.close();
        }
    }

    public void updateTitle(String title) {
        if (Objects.equals(title, "")) {
            compilerStage.setTitle("Compiler");
        } else {
            compilerStage.setTitle("Compiler - " + title);
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
        compilerStage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double newTextAreaWidth = compilerStage.getWidth() - 38;
            splitPane.setPrefWidth(newTextAreaWidth);
            lineColumnText.setLayoutX(10);
        });
    }

    public void autoResizeSplitPaneHeight(SplitPane splitPane, Text lineColumnText) {
        compilerStage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            splitPane.setPrefHeight(compilerStage.getHeight() - 160);
            lineColumnText.setLayoutY(compilerStage.getHeight() - 75);
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
        File file = fileChooser.showOpenDialog(compilerStage);
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
        return fileChooser.showSaveDialog(compilerStage);
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
        this.tableStage = new Stage();
        TableView<Instruction> table = new TableView();

        TableColumn<Instruction, Integer> pointerColumn = new TableColumn<>("Pointer");
        pointerColumn.setCellValueFactory(new PropertyValueFactory<>("pointer"));

        TableColumn<Instruction, String> codeColumn = new TableColumn<>("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Instruction, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        table.getColumns().addAll(pointerColumn, codeColumn, addressColumn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        for (Instruction inst : instructions) {
            table.getItems().add(inst);
        }

        Scene tableScene = new Scene(table, 300, 220);
        this.tableStage.setScene(tableScene);
        this.tableStage.setTitle("Object Code");
        this.tableStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
