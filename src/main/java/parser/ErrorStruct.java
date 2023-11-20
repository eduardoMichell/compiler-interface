package parser;

public class ErrorStruct {
    private ParseException error = null;
    private String msg = null;

    public ErrorStruct(String msg, ParseException error) {
        this.error = error;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public ParseException getError() {
        return error;
    }

    public String expected() {
        boolean haveMoreThanOne = false;
        StringBuilder expectedMsg = new StringBuilder();
        expectedMsg.append(" (");
        for (int i = 0; i < this.error.expectedTokenSequences.length; i++) {
            if (this.error.expectedTokenSequences.length == 1) {
                expectedMsg.append(fromTo(LangParserConstants.tokenImage[this.error.expectedTokenSequences[i][0]])).append("");
            } else {
                for (int j = 0; j < this.error.expectedTokenSequences[i].length; j++) {
                    haveMoreThanOne = true;
                    expectedMsg.append(fromTo(LangParserConstants.tokenImage[this.error.expectedTokenSequences[i][j]])).append(", ");
                }
            }
        }
        if(haveMoreThanOne){
            expectedMsg = new StringBuilder(expectedMsg.substring(0, expectedMsg.length() - 2));
        }
        expectedMsg.append(") ");
        return expectedMsg.toString();
    }

    public String fromTo(String token){
        switch (token) {
            case "<DO>":
                return "do";
            case "<IS>":
                return "is";
            case "<AS>":
                return "as";
            case "<AND>":
                return "and";
            case "<ALL>":
             return "all";
            case "<THIS>":
             return "this";
            case "<BODY>":
             return "body";
            case "<TYPE>":
             return "type";
            case "<REAL>":
             return "real";
            case "<STRING>":
             return "string";
            case "<LOGIC>":
             return "logic";
            case "<ENUM>":
             return "enum";
            case "<READ>":
             return "read";
            case "<WRITE>":
             return "write";
            case "<REPEAT>":
             return "repeat";
            case "<VARIABLE>":
             return "variable";
            case "<CONSTANT>":
             return "constant";
            case "<RESULT>":
             return "result";
            case "<INTEGER>":
             return "integer";
            case "<AVALIATE>":
             return "avaliate";
            case "<DESCRIPTION>":
             return "description";
            case "<DECLARATION>":
             return "declaration";
            case "<DESIGNATE>":
             return "designate";
            case "<TRUE>":
             return "true";
            case "<UNTRUE>":
             return "untrue";
            case "<OR_LOGIC>":
             return "|";
            case "<AND_LOGIC>":
             return "&";
            case "<NEGATE_LOGIC>":
             return "!";
            case "<PLUS>":
             return "+";
            case "<SUBTRACTION>":
             return "&";
            case "<MULTIPLY>":
             return "*";
            case "<DIVISION>":
             return "/";
            case "<INTEGER_DIVISION>":
             return "%";
            case "<DIVISION_REMAINDER>":
             return "%%";
            case "<POTENCE>":
             return "**";
            case "<NOT_EQUAL>":
             return "!=";
            case "<ATTRIBUTION>":
             return "=";
            case "<EQUAL>":
             return "==";
            case "<GREATER_THEN>":
             return ">>";
            case "<LESS_THEN>":
             return "<<";
            case "<LESS_EQUALS>":
             return "<<=";
            case "<GREATER_EQUALS>":
             return ">>=";
            case "<OPEN_KEYS>":
             return "{";
            case "<CLOSE_KEYS>":
             return "}";
            case "<OPEN_BOX>":
             return "[";
            case "<CLOSE_BOX>":
             return "]";
            case "<OPEN_BRACKETS>":
             return "(";
            case "<CLOSE_BRACKETS>":
             return ")";
            case "<SEMI_COLUMN>":
             return ",";
            case "<DOT>":
             return ".";
            case "<IDENTIFIER>":
             return "Identifier";
            case "<LITERAL_CONSTANT>":
             return "Literal Constant";
            case "<DOUBLE_QUOTE_CONST>":
             return "Literal Constant with double quote";
            case "<SINGLE_QUOTE_CONST>":
             return "Literal Constant with single quote";
            case "<INTEGER_NUMERICAL_CONSTANT>":
             return "Integer Numerical Constant";
            case "<REAL_NUMERICAL_CONSTANT>":
             return "Real Numerical Constant";
            case "<DIGIT>":
             return "Digit";
            case "<SYMBOL>":
             return "_";
            case "<MINUS>":
             return "-";
            case "<SINGLE_QUOTE>":
             return "'";
            case "<DOUBLE_QUOTE>":
             return "\"";
            case "<ASCII>":
             return "Any Character of Ascii";
            case "<SMALL_LETTER>":
             return "Small Letter";
            case "<CAPITAL_LETTER>":
             return "Capital Letter";
            case "<LETTER>":
                return "Letter";
            case "<EOF>":
                return "End of File";
            default:
                return token;
        }
    }
    
    
}
