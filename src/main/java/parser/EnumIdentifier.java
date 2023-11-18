package parser;

import java.util.ArrayList;

public class EnumIdentifier {
    private String identifier;
    private ArrayList<String> identifierList;
    public EnumIdentifier(String identifier, ArrayList<String> identifierList) {
        this.identifier = identifier;
        this.identifierList = identifierList;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public ArrayList<String> getIdentifierList() {
        return identifierList;
    }

    public void setIdentifierList(ArrayList<String> identifierList) {
        this.identifierList = identifierList;
    }

    public void addIdentifier(String identifier){
        this.identifierList.add(identifier);
    }
}
