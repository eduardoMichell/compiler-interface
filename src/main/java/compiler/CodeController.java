package compiler;

import LexicalAnalysis.LexicalAnalysis;
import javafx.scene.control.TextArea;
import LexicalAnalysis.ParseException;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.*;
import java.util.Arrays;

public class CodeController {
    public LexicalAnalysis lexicalAnalysis;
    private String code;
    private String fileName;
    private File file;

    Clipboard clipboard = Clipboard.getSystemClipboard();

    private boolean isFileEdited;

    public boolean isFileEdited() {
        return isFileEdited;
    }

    public void setFileEdited(boolean fileEdited) {
        isFileEdited = fileEdited;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public CodeController(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void openFile(TextArea codeTextArea, CompilerApplication screen) throws IOException {
        File file = screen.openFile("Open File");
        if(file != null) {
            codeTextArea.setText("");
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            String line;
            while ((line = reader.readLine()) != null) {
                this.addTextToCodeTextArea(codeTextArea, line);
                screen.updateTitle(file.getName());
            }
            this.setFile(file);
            this.setFileName(file.getName());
            this.setCode(codeTextArea.getText());
            this.setFileEdited(false);
            reader.close();
        }

    }

    public void saveFile(String code, File file) throws IOException {
        this.setFileEdited(false);
        this.setFile(file);
        this.setFileName(file.getName());
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(code);
        bufferedWriter.close();
    }

    public void newFile(int archivesSize){
        this.file = null;
        this.code = "";
        this.fileName = "compiler" + archivesSize + ".txt";
        this.isFileEdited = true;
    }

    void copySelectedText(TextArea codeTextArea){
        String selectedText = codeTextArea.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);
        }
    }

    void cutSelectedText(TextArea codeTextArea){
        String selectedText = codeTextArea.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);
            int start = codeTextArea.getSelection().getStart();
            int end = codeTextArea.getSelection().getEnd();
            codeTextArea.deleteText(start, end);
        }
    }

    void deleteSelectedText(TextArea codeTextArea){
        String selectedText = codeTextArea.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            int start = codeTextArea.getSelection().getStart();
            int end = codeTextArea.getSelection().getEnd();
            codeTextArea.deleteText(start, end);
        }
    }

    void pasteSelectedText(TextArea codeTextArea){
        if (clipboard.hasString()) {
            String clipboardText = clipboard.getString();
            codeTextArea.insertText(codeTextArea.getCaretPosition(), clipboardText);
        }
    }

    void selectAllText(TextArea codeTextArea){
        codeTextArea.selectAll();
    }

    void deselectAllText(TextArea codeTextArea){
        codeTextArea.deselect();
    }
    void addTextToCodeTextArea(TextArea codeTextArea, String text){
        codeTextArea.setText(codeTextArea.getText() + text);
    }

    void eraseCodeTextArea(TextArea codeTextArea){
        codeTextArea.setText("");
    }


    void compile(String code, TextArea consoleTextArea) {
        LexicalAnalysis parser = null;
       try {
           parser = new LexicalAnalysis(new BufferedReader(new StringReader(code)));
           parser.MainRule();
           consoleTextArea.setText(consoleTextArea.getText() + parser.getResult());
           System.out.println(parser.token.kind);
       } catch (ParseException e){
           System.out.println(parser.getResult());
           System.out.println(e);
           consoleTextArea.setText(consoleTextArea.getText() + parser.getResult());
       }

    }





}
