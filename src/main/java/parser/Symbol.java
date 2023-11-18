package parser;

public class Symbol {
    private String identifier;
    private String category;
    private String attrOne;
    private String attrTwo;

    public Symbol(String identifier, String category, String attrOne, String attrTwo) {
        this.identifier = identifier;
        this.category = category;
        this.attrOne = attrOne;
        this.attrTwo = attrTwo;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAttrOne() {
        return attrOne;
    }

    public void setAttrOne(String attrOne) {
        this.attrOne = attrOne;
    }

    public String getAttrTwo() {
        return attrTwo;
    }

    public void setAttrTwo(String attrTwo) {
        this.attrTwo = attrTwo;
    }
}
