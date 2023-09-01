package compiler;
import LexicalAnalysis.ParseException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.util.*;

public class CompilerController  implements Initializable {

    private int archivesSize = 1;

    CompilerApplication screen;

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
    private Button newFile = new Button();

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

    private CodeController codeController;

    public CompilerController() {
        this.screen = CompilerApplication.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initialSettings();
        this.screen.updateTextAreaWidth(codeTextArea, consoleTextArea);
        this.screen.updateTextAreaHeight(codeTextArea, consoleTextArea);
    }

    @FXML
    void onCodeAreaKeyPressed(KeyEvent event) {
        this.codeController.setCode(this.codeTextArea.getText()+event.getText());
        this.codeController.setFileEdited(true);
        this.screen.updateTitle(this.codeController.getFileName() + "*");
    }

    @FXML
    void onClickNewFile(ActionEvent event) {
        this.newFile();
    }

    void newFile(){
        if(!this.codeController.isFileEdited()) {
            this.codeTextArea.setText("");
            this.codeController.newFile(this.archivesSize);
            this.archivesSize++;
            this.screen.updateTitle(this.codeController.getFileName() + "*");
            this.disableIDE(false);
            this.disableIDE(false);
        } else {
            this.screen.saveFile(this.codeController.getFileName(), this.codeTextArea.getText(), this.codeController, this);
            if(!this.codeController.isFileEdited()){
                this.codeTextArea.setText("");
                this.codeController.newFile(this.archivesSize);
                this.archivesSize++;
                this.screen.updateTitle(this.codeController.getFileName() + "*");
                this.disableIDE(false);
                this.disableIDE(false);
            }
        }
    }

    @FXML
    void onClickOpenFile(ActionEvent event) throws IOException {
        this.openFile();
    }

    void openFile() throws IOException {
        if (this.codeController.isFileEdited()) {
            this.screen.saveFile(this.codeController.getFileName(), this.codeTextArea.getText(), this.codeController, this);
        }
        this.codeController.openFile(codeTextArea, screen);
        if(this.codeController.getFile() != null){
            this.disableIDE(false);
        }
    }

    void disableIDE(boolean variable){
        this.codeTextArea.setDisable(variable);
        this.cutText.setDisable(variable);
        this.copyText.setDisable(variable);
        this.pasteText.setDisable(variable);
        this.compile.setDisable(variable);
        this.saveFile.setDisable(variable);
    }

    void eraseAll(){
        this.codeController.setFile(null);
        this.codeController.setFileName("");
        this.codeController.setCode("");
        this.codeController.setFileEdited(false);
        this.codeTextArea.setText("");
        this.consoleTextArea.setText("");
        this.disableIDE(true);
        this.screen.updateTitle("");
    }

    void cancelSaveFile(){
      this.eraseAll();
    }

    void saveFile() throws IOException {
        if(this.codeController.isFileEdited()) {
            if(this.codeController.getFile() != null){
                this.codeController.saveFile(this.codeTextArea.getText(), this.codeController.getFile());
                this.screen.updateTitle(this.codeController.getFileName());
            } else {
                File newFile = screen.chooseFilePath("Save As", this.codeController.getFileName());
                if(newFile != null) {
                    this.codeController.saveFile(this.codeTextArea.getText(), newFile);
                    this.screen.updateTitle(newFile.getName());
                }
            }
        }
    }
    @FXML
    void onClickSaveFile(ActionEvent event) throws IOException {
        this.saveFile();
    }

    @FXML
    void onClickCutText(ActionEvent event) {
        this.codeController.cutSelectedText(codeTextArea);
    }

    @FXML
    void onClickCopyText(ActionEvent event) {
        this.codeController.copySelectedText(codeTextArea);
    }

    @FXML
    void onClickPastText(ActionEvent event) {
        this.codeController.pasteSelectedText(codeTextArea);
    }

    @FXML
    void onClickCompile(ActionEvent event) throws ParseException {
        this.consoleTextArea.setText("");
        this.codeController.compile(this.codeTextArea.getText(), consoleTextArea);
    }


    @FXML
    void onClickRun(ActionEvent event) {

    }

    @FXML
    void onClickStop(ActionEvent event) {
    }

    @FXML
    void menuBarFileNewOnClick(ActionEvent event) {
        this.newFile();
    }
      @FXML
    void menuBarFileOpenOnClick(ActionEvent event) throws IOException {
          if(!this.codeController.isFileEdited()) {
              this.openFile();
          } else {
              this.screen.saveFile(this.codeController.getFileName(), this.codeTextArea.getText(), this.codeController, this);
              this.openFile();
          }
    }

    @FXML
    void menuBarFileCloseOnClick(ActionEvent event) {
        if(!this.codeController.isFileEdited()) {
            this.eraseAll();
        } else {
            this.screen.saveFile(this.codeController.getFileName(), this.codeTextArea.getText(), this.codeController, this);
            this.eraseAll();
        }
    }

    @FXML
    void menuBarFileSaveOnClick(ActionEvent event) throws IOException {
        this.saveFile();
    }
    @FXML
    void menuBarSaveAsNewOnClick(ActionEvent event) throws IOException {
        File newFile = screen.chooseFilePath("Save As", this.codeController.getFileName());
        if(newFile != null) {
            this.codeController.saveFile(this.codeTextArea.getText(), newFile);
            this.screen.updateTitle(newFile.getName());
        }

    }

    @FXML
    void menuBarFileExitOnClick(ActionEvent event) {
        if(!this.codeController.isFileEdited()) {
           Platform.exit();
        } else {
            this.screen.saveFile(this.codeController.getFileName(), this.codeTextArea.getText(), this.codeController, this);
        }
    }

    @FXML
    void menuBarEditCopyOnClick(ActionEvent event) {
        this.codeController.copySelectedText(codeTextArea);
    }

    @FXML
    void menuBarEditPasteOnClick(ActionEvent event) {
        this.codeController.pasteSelectedText(codeTextArea);
    }

    @FXML
    void menuBarEditCutOnClick(ActionEvent event) {
        this.codeController.cutSelectedText(codeTextArea);
    }

    @FXML
    void menuBarEditDeleteOnClick(ActionEvent event) {
        this.codeController.deleteSelectedText(codeTextArea);
    }

    @FXML
    void menuBarEditSelectAllOnClick(ActionEvent event) {
        this.codeController.selectAllText(codeTextArea);
    }

    @FXML
    void menuBarEditUnselectAllOnClick(ActionEvent event) {
        this.codeController.deselectAllText(codeTextArea);
    }

    @FXML
    void menuBarCompileOnClick(ActionEvent event) {
        this.consoleTextArea.setText("");
        this.codeController.compile(this.codeTextArea.getText(), consoleTextArea);
    }

    @FXML
    void menuBarRunOnClick(ActionEvent event) {
        System.out.println("Clicou em executar");
    }
    @FXML
    void menuBarStopOnClick(ActionEvent event) {
        System.out.println("Clicou em parar execução");
    }

    private void setImages() {
        String iconsPath = "/icons/";
        this.newFile.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconsPath + "new-file-icon.png")))));
        newFile.setTooltip(new Tooltip("Novo Arquivo"));

        this.openFile.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconsPath + "open-file-icon.png")))));
        openFile.setTooltip(new Tooltip("Abrir Arquivo"));

        this.saveFile.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconsPath + "save-file-icon.png")))));
        saveFile.setTooltip(new Tooltip("Salvar Arquivo"));

        this.cutText.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconsPath + "cut-icon.png")))));
        cutText.setTooltip(new Tooltip("Recortar Texto"));

        this.copyText.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconsPath + "copy-icon.png")))));
        copyText.setTooltip(new Tooltip("Copiar Texto"));

        this.pasteText.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconsPath + "paste-icon.png")))));
        pasteText.setTooltip(new Tooltip("Colar Texto"));

        this.compile.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconsPath + "compile-icon.png")))));
        compile.setTooltip(new Tooltip("Compilar Código"));

        this.run.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconsPath + "run-icon.png")))));
        run.setTooltip(new Tooltip("Executar Código"));

        this.stop.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconsPath + "stop-icon.png")))));
        stop.setTooltip(new Tooltip("Parar Execução"));
    }

    private void initialSettings() {
        this.codeController = new CodeController(codeTextArea.getText());
        this.codeTextArea.setDisable(true);
        this.cutText.setDisable(true);
        this.copyText.setDisable(true);
        this.pasteText.setDisable(true);
        this.compile.setDisable(true);
        this.run.setDisable(true);
        this.stop.setDisable(true);
        this.saveFile.setDisable(true);
        this.setImages();
    }

}