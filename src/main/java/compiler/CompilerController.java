package compiler;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class CompilerController  implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextArea consoleTextArea;

    @FXML
    private TextArea codeTextArea;

    @FXML
    private Button compile = new Button();

    @FXML
    private Button copyText = new Button();

    @FXML
    private Button cutText = new Button();

    @FXML
    private Button newArchiveButton = new Button();

    @FXML
    private Button openFile = new Button();

    @FXML
    private Button pasteText = new Button();

    @FXML
    private Button run = new Button();

    @FXML
    private Button saveFile = new Button();

    @FXML
    private Button stop = new Button();

    public CompilerController() {


    }
    /**
     * start of screen
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("teste");
        this.setImages();
//        consoleTextArea.prefWidthProperty().bind(anchorPane.widthProperty().subtract(25));
//        codeTextArea.prefWidthProperty().bind(anchorPane.widthProperty().subtract(25));
    }

    private void setImages() {
        String iconsPath = "";


    }

}