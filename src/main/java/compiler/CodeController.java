package compiler;

import parser.*;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CodeController {
    public LangParser LangParser;
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
        if (file != null) {
            codeTextArea.setText("");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                this.addTextToCodeTextArea(codeTextArea, line + "\n");
            }
            this.setFile(file);
            this.setFileName(file.getName());
            screen.updateTitle(file.getName());
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

    public void newFile(int archivesSize) {
        this.file = null;
        this.code = "";
        this.fileName = "compiler" + archivesSize + ".txt";
        this.isFileEdited = true;
    }

    void copySelectedText(TextArea codeTextArea) {
        String selectedText = codeTextArea.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);
        }
    }

    void cutSelectedText(TextArea codeTextArea) {
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

    void deleteSelectedText(TextArea codeTextArea) {
        String selectedText = codeTextArea.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            int start = codeTextArea.getSelection().getStart();
            int end = codeTextArea.getSelection().getEnd();
            codeTextArea.deleteText(start, end);
        }
    }

    void pasteSelectedText(TextArea codeTextArea) {
        if (clipboard.hasString()) {
            String clipboardText = clipboard.getString();
            codeTextArea.insertText(codeTextArea.getCaretPosition(), clipboardText);
        }
    }

    void selectAllText(TextArea codeTextArea) {
        codeTextArea.selectAll();
    }

    void deselectAllText(TextArea codeTextArea) {
        codeTextArea.deselect();
    }

    void addTextToCodeTextArea(TextArea codeTextArea, String text) {
        codeTextArea.appendText(text);
    }

    void eraseCodeTextArea(TextArea codeTextArea) {
        codeTextArea.setText("");
    }

    void compile(TextArea code, TextArea consoleTextArea, CompilerApplication screen) {
        LangParser parser = null;
        try {
            parser = new LangParser(new ByteArrayInputStream(code.getText().getBytes()));
            parser.eraseSemanticRules();
            parser.lexicalAnalyzer();
            consoleTextArea.setText(consoleTextArea.getText() + parser.getResult());
            if (parser.isValidLexical()) {
                parser = new LangParser(new ByteArrayInputStream(code.getText().getBytes()));
                parser.setOutput(new ArrayList<ErrorStruct>());
                parser.syntaxAnalyzer();
                if (parser.isValidSyntax()) {
                    if(parser.isValidSemantic()){
                        consoleTextArea.appendText("The code was compiled successfully\n");
                        screen.openTable(parser.getSemanticInstructions());
                    } else {
                        consoleTextArea.appendText("Found semantic errors:\n");

                        for (String err : parser.getSemanticErrors()) {
                            consoleTextArea.appendText(err + "\n");
                        }
                    }
                } else {
                    consoleTextArea.appendText("Found " + parser.getOutput().size() + " syntactic errors:\n");
                    for (ErrorStruct err : parser.getOutput()) {
                        consoleTextArea.appendText("Error: " + err.getMsg() + " located at [line:" + err.getError().currentToken.beginLine + "| column: "
                                + err.getError().currentToken.endColumn + "] expected:" + err.expected() + "\n");
                    }
                }
            } else {
                consoleTextArea.appendText("The lexical analyzer encountered a problem and was unable to continue parsing\n");
            }
        } catch (ParseException e) {
            consoleTextArea.setText(consoleTextArea.getText() + parser.getResult() + e.getMessage());
        }

    }


}
