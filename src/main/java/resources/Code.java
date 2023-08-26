package resources;

public class Code {
    private String code;
    private String fileName;

    public Code(String code) {
        this.code = code;
        this.fileName = "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
