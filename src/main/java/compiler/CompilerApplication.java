package compiler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;
import java.util.Optional;

public class CompilerApplication extends Application {

    FileChooser fileChooser = new FileChooser();
    private static CompilerApplication instance;
    public static CompilerApplication getInstance() {
        return instance;
    }

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

    public void updateTitle(String title) {
        if(Objects.equals(title, "")){
            stage.setTitle("Compilador");
        } else {
            stage.setTitle("Compilador - " + title);
        }
    }

    public void updateTextAreaWidth(TextArea codeTextArea, TextArea consoleTextArea){
        stage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double newTextAreaWidth = stage.getWidth() - 38;
            codeTextArea.setPrefWidth(newTextAreaWidth);
            consoleTextArea.setPrefWidth(newTextAreaWidth);
        });
    }

    public void updateTextAreaHeight(TextArea codeTextArea, TextArea consoleTextArea){
        stage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            codeTextArea.setPrefHeight(stage.getHeight() - 320);
            consoleTextArea.setLayoutY(stage.getHeight() - 240);
        });
    }

    public File openFile(String title){
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        return fileChooser.showOpenDialog(stage);
    }


    public File chooseFilePath(String title){
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
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

            if (result.get() == yesButton){
                if(controller.getFile() != null) {
                    controller.saveFile(code, controller.getFile());
                    this.updateTitle(controller.getFileName());
                } else {
                    File newFile = chooseFilePath("Save New File");
                    if(newFile != null){
                        controller.saveFile(code, newFile);
                        this.updateTitle(newFile.getName());
                    }
                }
            } else if (result.get() == noButton) {
                if(controller.isFileEdited()){
                    compilerController.cancelSaveFile();
                }
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
