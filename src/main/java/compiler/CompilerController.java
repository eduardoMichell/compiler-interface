package compiler;
import LexicalAnalysis.ParseException;
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

    private int archivesSize = 0;

    private String openedFile = "";

    private boolean isSaved = true;

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
    void buttonNewFileOnPressed(KeyEvent event) {
        this.codeController.setCode(this.codeController.getCode()+event.getText());
        this.isSaved = false;
        this.screen.updateTitle(this.codeController.getFileName() + "*");
    }

    @FXML
    void onCodeAreaKeyPressed(KeyEvent event) {
        this.codeController.setCode(this.codeTextArea.getText()+event.getText());
        this.codeController.setFileEdited(true);
        this.screen.updateTitle(this.codeController.getFileName() + "*");
    }

    @FXML
    void onClickNewFile(ActionEvent event) {
        this.codeController = new CodeController(codeTextArea.getText());
        this.archivesSize++;
        this.codeController.setFileName("compilador" + this.archivesSize + ".txt");
        this.screen.updateTitle(this.codeController.getFileName());
        this.openedFile = this.codeController.getFileName();
        this.disableIDE(false);
    }



    @FXML
    void onClickOpenFile(ActionEvent event) throws IOException {
        this.eraseAll();
        this.codeController.openFile(codeTextArea, screen);
        this.disableIDE(false);
    }

    void disableIDE(boolean variable){
        this.codeTextArea.setDisable(variable);
        this.cutText.setDisable(variable);
        this.copyText.setDisable(variable);
        this.pasteText.setDisable(variable);
        this.compile.setDisable(variable);
    }

    void eraseAll(){
        this.codeController.setFile(null);
        this.codeController.setFileName("");
        this.codeController.setCode("");

        this.codeTextArea.setText("");
        this.consoleTextArea.setText("");

        this.disableIDE(true);

        this.screen.updateTitle("");
    }

    @FXML
    void onClickSaveFile(ActionEvent event) {

    }

    @FXML
    void onClickCutText(ActionEvent event) {

    }

    @FXML
    void onClickCopyText(ActionEvent event) {

    }

    @FXML
    void onClickPastText(ActionEvent event) {

    }

    @FXML
    void onClickCompile(ActionEvent event) throws ParseException {
        this.consoleTextArea.setText("");
//        String[] tokens = this.codeController.getCode().split(" ");
//        for (String token : tokens) {
            InputStream concatenatedStream = getCodeInputStream();
            this.codeController.compile(concatenatedStream, consoleTextArea);
//        }

    }

    InputStream getCodeInputStream( ){

        String[] stringArray = this.codeController.getCode().split(" ");

        List<InputStream> inputStreams = new ArrayList<>();

        for (String str : stringArray) {
            inputStreams.add(new ByteArrayInputStream(str.getBytes()));
        }
//        List<InputStream> inputStreams = new ArrayList<>();
//        inputStreams.add(new ByteArrayInputStream(token.getBytes()));
        return new SequenceInputStream(Collections.enumeration(inputStreams));
    }

    @FXML
    void onClickRun(ActionEvent event) {

    }

    @FXML
    void onClickStop(ActionEvent event) {
    }

    @FXML
    void menuBarFileNewOnClick(ActionEvent event) {

    }
      @FXML
    void menuBarFileOpenOnClick(ActionEvent event) throws IOException {
        this.eraseAll();
        this.codeController.openFile(codeTextArea, screen);
        this.disableIDE(false);
    }
    @FXML
    void menuBarFileCloseOnClick(ActionEvent event) {
        this.eraseAll();
    }

    @FXML
    void menuBarFileSaveOnClick(ActionEvent event) {
        System.out.println("Clicou em salvar");
    }
    @FXML
    void menuBarSaveAsNewOnClick(ActionEvent event) {
        System.out.println("Clicou em salvar como");
    }

    @FXML
    void menuBarFileExitOnClick(ActionEvent event) {
        System.out.println("Clicou em sair");
    }

    @FXML
    void menuBarEditCopyOnClick(ActionEvent event) {
        System.out.println("Clicou em copiar");
    }

    @FXML
    void menuBarEditPasteOnClick(ActionEvent event) {
        System.out.println("Clicou em colar");
    }

    @FXML
    void menuBarEditCutOnClick(ActionEvent event) {
        System.out.println("Clicou em recortar");
    }

    @FXML
    void menuBarEditDeleteOnClick(ActionEvent event) {
        System.out.println("Clicou em deletar");
    }

    @FXML
    void menuBarEditSelectAllOnClick(ActionEvent event) {
        System.out.println("Clicou em selecionar todos");
    }

    @FXML
    void menuBarEditUnselectAllOnClick(ActionEvent event) {
        System.out.println("Clicou em desselecionar todos");
    }

    @FXML
    void menuBarCompileOnClick(ActionEvent event) {
        System.out.println("Clicou em compilar");
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
        this.setImages();
    }

}