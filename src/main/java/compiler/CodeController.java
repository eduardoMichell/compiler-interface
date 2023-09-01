package compiler;

import LexicalAnalysis.LexicalAnalysis;
import javafx.scene.control.TextArea;
import LexicalAnalysis.ParseException;
import java.io.*;
import java.util.Arrays;

public class CodeController {
    public LexicalAnalysis lexicalAnalysis;
    private String code;
    private String fileName;

    private File file;

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
        BufferedReader reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        String line;
        while ((line = reader.readLine()) != null) {
            this.addTextToCodeTextArea(codeTextArea, line);
            screen.updateTitle(file.getName());
            this.setFile(file);
            this.setFileName(file.getName());
            this.setCode(codeTextArea.getText());
        }
        reader.close();
    }



    void addTextToCodeTextArea(TextArea codeTextArea, String text){
        codeTextArea.setText(codeTextArea.getText() + text);
    }

    void eraseCodeTextArea(TextArea codeTextArea){
        codeTextArea.setText("");
    }


    void compile(InputStream code, TextArea consoleTextArea) throws ParseException {
        LexicalAnalysis parser = null;
       try {
           parser = new LexicalAnalysis(code);
           parser.MainRule();
           consoleTextArea.setText(consoleTextArea.getText() + parser.getResult());
       } catch (ParseException e){
           System.out.println(Arrays.toString(e.tokenImage));
           System.out.println( e.currentToken);
           System.out.println(e.getMessage());
           System.out.println(parser);
           System.out.println(parser.getResult());
       }

    }




}
