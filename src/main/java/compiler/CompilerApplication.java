package compiler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CompilerApplication extends Application {
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
        stage.setMinWidth(917);
        stage.setMinHeight(700);

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
            double newTextAreaWidth = stage.getWidth() - 40;
            codeTextArea.setPrefWidth(newTextAreaWidth);
            consoleTextArea.setPrefWidth(newTextAreaWidth);
        });
    }

    public static void main(String[] args) {
        launch();
    }

}
