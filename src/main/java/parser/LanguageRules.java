package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LanguageRules {
    private static final String WRITE_ALL_THIS = "write all this";
    private static final String WRITE_THIS = "write this";
    private static final String AS_CONSTANT = "as constant";
    private static final String AS_VARIABLE = "as variable";
    private static final String ASSIGNMENT = "assignment";
    private static final String INPUT = "input";
    private String context;
    private Integer VT;
    private Integer VP;
    private Integer VI;
    private Integer TVI;
    private Integer type;
    private Integer pointer;
    private Boolean indexedVariable;
    private String output;
    private List<String> deviationStack;
    private List<Symbol> symbolTable;
    private List<EnumIdentifier> enumSymbolTable;
    private List<Instruction> instructionStack;
    private Token identifierRule3;
    private Token identifierRule10;
    private Token identifierRule24;
    private Token identifierRule24Error;
    private Integer integerConstant12;
    private List<Token> existingErrors;
    private List<String> error;
    private List<String> attributeList;
    boolean haveError;

    public LanguageRules() {
        this.context = "";
        this.VT = 0;
        this.VP = 0;
        this.VI = 0;
        this.TVI = 0;
        this.type = 0;
        this.pointer = 1;
        this.indexedVariable = false;
        this.output = "";
        this.deviationStack = new ArrayList<>();
        this.symbolTable = new ArrayList<>();
        this.instructionStack = new ArrayList<>();
        this.enumSymbolTable = new ArrayList<>();
        this.attributeList = new ArrayList<>();
        this.identifierRule3 = null;
        this.identifierRule10 = null;
        this.identifierRule24 = null;
        this.identifierRule24Error = null;
        this.integerConstant12 = 0;
        this.haveError = false;
        this.error = new ArrayList<>();
        this.existingErrors = new ArrayList<>();
    }

    public boolean haveError() {
        return haveError;
    }

    public List<String> getError() {
        return error;
    }

    public List<Instruction> getInstructionStack() {
        return instructionStack;
    }

    private void stack(String item) {
        deviationStack.add(item);
    }

    public String unstack() {
        if (!deviationStack.isEmpty()) {
            int lastIndex = deviationStack.size() - 1;
            String unstackedItem = deviationStack.get(lastIndex);
            deviationStack.remove(lastIndex);
            return unstackedItem;
        } else {
            return null;
        }
    }

    private boolean isSymbolPresent(String identifier) {
        boolean haveSymbol = false;
        for (Symbol symbol : symbolTable) {
            System.out.println(symbol.getIdentifier());
            if (symbol.getIdentifier().equals(identifier)) {
                haveSymbol = true;
                break;
            }
        }
        return haveSymbol;
    }

    private Symbol recoverSymbol(String identifier) {
        for (Symbol symbol : symbolTable) {
            if (symbol.getIdentifier().equals(identifier)) {
                return symbol;
            }
        }
        return null;
    }

    private boolean isPresentEnumSymbolTable(String identifier) {
        boolean haveSymbol = false;
        for (EnumIdentifier symbol : enumSymbolTable) {
            if (symbol.getIdentifier().equals(identifier)) {
                haveSymbol = true;
                break;
            }
        }
        return haveSymbol;
    }

    private boolean isPresentEnumIdentifierList(String identifier) {
        boolean haveSymbol = false;
        for (EnumIdentifier symbol : enumSymbolTable) {
            for (String constantIdentifier : symbol.getIdentifierList())
                if (constantIdentifier.equalsIgnoreCase(identifier)) {
                    haveSymbol = true;
                    break;
                }
        }
        return haveSymbol;
    }

    private EnumIdentifier recoverEnumSymbol(String identifier) {
        for (EnumIdentifier symbol : enumSymbolTable) {
            if (symbol.getIdentifier().equalsIgnoreCase(identifier)) {
                return symbol;
            }
        }
        return null;
    }

    public void insertInstructionInEnumIdentifier(String enumIdentifier, String instruction) {
        for (EnumIdentifier instructions : enumSymbolTable) {
            if (instructions.getIdentifier().equals(enumIdentifier)) {
                instructions.getIdentifierList().add(instruction);
                return;
            }
        }
    }

    public void unstackLastInstructionAndUpdate() {
        String lastOfStack = unstack();
        System.out.println("AQUI");
        for (Instruction instruction : instructionStack) {
            if (instruction.getPointer() == Integer.parseInt(lastOfStack)) {
                System.out.println("ponteiro da instrucao: " + instruction.getPointer() + instruction.getAddress() + instruction.getCode());
                System.out.println("ponteiro da last of stack: " + Integer.parseInt(lastOfStack));
                System.out.println("instruction address: " + instruction.getAddress());
                instruction.setAddress(String.valueOf(pointer + 1));
                System.out.println("instruction address: " + instruction.getAddress());
                break;
            }
        }
    }


    public void rule1(Token token) {
        String identifier = token.image;
        symbolTable.add(new Symbol(identifier, "0", "-", "-"));
        System.out.println("regra 1");
        printInstructions();
    }

    public void rule2() {
        instructionStack.add(new Instruction(pointer, "STP", "0"));
        System.out.println("regra 2");
        printInstructions();
    }

    public void rule3(Token token) {
        String identifier = token.image;
        if (isSymbolPresent(identifier) || isPresentEnumSymbolTable(identifier)) {
            this.addError(token, "Identifier '" + identifier + "' already declared");
        } else {
            EnumIdentifier enumIdentifier = new EnumIdentifier(identifier, new ArrayList<>());
            this.identifierRule3 = token;
            this.enumSymbolTable.add(enumIdentifier);
        }
        System.out.println("regra 3");
        printInstructions();
    }

    public void rule4(Token token) {
        String identifier = token.image;
        if (isSymbolPresent(identifier) || isPresentEnumSymbolTable(identifier) || isPresentEnumIdentifierList(identifier)) {
            this.addError(token, "Identifier '" + identifier + "' already declared");
        } else {
            insertInstructionInEnumIdentifier(this.identifierRule3.image, identifier);
        }
        System.out.println("regra 4");
        printInstructions();
    }

    public void rule5() {
        context = AS_CONSTANT;
        VI = 0;
        TVI = 0;
        System.out.println("regra 5");
        printInstructions();
    }

    public void rule6() {
        int latestEntries = VP + VI;
        for (int i = this.symbolTable.size() - latestEntries; i < this.symbolTable.size(); i++) {
            this.symbolTable.get(i).setCategory(type.toString());
        }
        VP = VP + TVI;
        switch (type) {
            case 1:
            case 5:
                instructionStack.add(new Instruction(pointer, "ALI", VP.toString()));
                pointer += 1;
                break;
            case 2:
            case 6:
                instructionStack.add(new Instruction(pointer, "ALR", VP.toString()));
                pointer += 1;
                break;
            case 3:
            case 7:
                instructionStack.add(new Instruction(pointer, "ALS", VP.toString()));
                pointer += 1;
                break;
            case 4:
                instructionStack.add(new Instruction(pointer, "ALB", VP.toString()));
                pointer += 1;
                break;
            default:
                break;
        }

        if (type == 1 || type == 2 || type == 3 || type == 4) {
            VP = 0;
            VT = 0;
            TVI = 0;
        }
        System.out.println("regra 6");
        printInstructions();
    }

    public void rule7(Token token) {
        String identifier = token.image;
        switch (type) {
            case 5:
                instructionStack.add(new Instruction(pointer, "LDI", identifier));
                pointer += 1;
                break;
            case 6:
                instructionStack.add(new Instruction(pointer, "LDR", identifier));
                pointer += 1;
                break;
            case 7:
                instructionStack.add(new Instruction(pointer, "LDS", identifier));
                pointer += 1;
                break;
            default:
                break;
        }

        instructionStack.add(new Instruction(pointer, "STC", VP.toString()));
        pointer += 1;
        VP = 0;
        System.out.println("regra 7");
        printInstructions();
    }

    public void rule8() {
        context = AS_VARIABLE;
        System.out.println("regra 8");
        printInstructions();
    }

    public void rule9(Token token) {
        String identifier = token.image;
        if (isSymbolPresent(identifier) || isPresentEnumSymbolTable(identifier) || isPresentEnumIdentifierList(identifier)) {
            this.addError(token, "Identifier '" + identifier + "' already declared");
        } else {
            VT += 1;
            VP += 1;
            symbolTable.add(new Symbol(identifier, "?", VT.toString(), "-"));
        }
        System.out.println("regra 9");
        printInstructions();
    }

    public void rule10(Token token) {
        String identifier = token.image;
        if (context.equals(AS_VARIABLE)) {
            if (isSymbolPresent(identifier) || isPresentEnumSymbolTable(identifier) || isPresentEnumIdentifierList(identifier)) {
                this.addError(token, "Identifier '" + identifier + "' already declared");
            } else {
                indexedVariable = false;
                identifierRule10 = token;
            }
        }
        if (context.equals(ASSIGNMENT) || context.equals(INPUT)) {
            indexedVariable = false;
            identifierRule10 = token;
        }
        System.out.println("regra 10");
        printInstructions();
    }

    public void rule11() {
        Symbol objIdentifierRule10;
        if (identifierRule10 != null) {
            objIdentifierRule10 = recoverSymbol(identifierRule10.image);
        } else {
            objIdentifierRule10 = null;
        }
        switch (context) {
            case AS_VARIABLE:
                if (!indexedVariable) {
                    if (identifierRule10 != null) {
                        VT += 1;
                        VP += 1;
                        symbolTable.add(new Symbol(identifierRule10.image, "?", VT.toString(), "-"));
                    }
                } else {
                    if (identifierRule10 != null) {
                        VI += 1;
                        TVI = TVI + integerConstant12;
                        symbolTable.add(new Symbol(identifierRule10.image, "?", String.valueOf(VT + 1), integerConstant12.toString()));
                        VT = VT + integerConstant12;
                    }
                }
                break;
            case ASSIGNMENT:
                System.out.println("'E AQUI" + identifierRule10.image + " " + isSymbolPresent(identifierRule10.image) + " + " + objIdentifierRule10.getCategory());
                if (isSymbolPresent(identifierRule10.image) &&
                        (objIdentifierRule10.getCategory().equals("1")
                                || objIdentifierRule10.getCategory().equals("2")
                                || objIdentifierRule10.getCategory().equals("3")
                                || objIdentifierRule10.getCategory().equals("4"))) {
                    if (objIdentifierRule10.getAttrTwo().equals("-")) {
                        if (!indexedVariable) {
                            attributeList.add(objIdentifierRule10.getAttrOne());
                        } else {
                            this.addError(identifierRule10, "'" + identifierRule10.image + "' is a non-indexed variable identifier");
                        }
                    } else {
                        if (indexedVariable) {
                            attributeList.add(Integer.toString(Integer.parseInt(objIdentifierRule10.getAttrOne()) + integerConstant12 - 1));
                        } else {
                            this.addError(identifierRule10, "Indexed variable identifier requires index");
                        }
                    }
                } else {
                    this.addError(identifierRule10, "Undeclared identifier or program identifier, constant or enumerated type");
                }
                break;
            case INPUT:
                if (isSymbolPresent(identifierRule10.image) &&
                        (objIdentifierRule10.getCategory().equals("1")
                                || objIdentifierRule10.getCategory().equals("2")
                                || objIdentifierRule10.getCategory().equals("3")
                                || objIdentifierRule10.getCategory().equals("4"))) {
                    Symbol aux = recoverSymbol(objIdentifierRule10.getIdentifier());
                    if (objIdentifierRule10.getAttrTwo().equals("-")) {
                        if (!indexedVariable) {
                            instructionStack.add(new Instruction(pointer, "REA", aux.getCategory()));
                            pointer = pointer + 1;
                            instructionStack.add(new Instruction(pointer, "STR", objIdentifierRule10.getAttrOne()));
                            pointer = pointer + 1;
                        } else {
                            this.addError(identifierRule10, identifierRule10.image + "' is a non-indexed variable identifier");
                        }
                    } else {
                        if (indexedVariable) {
                            instructionStack.add(new Instruction(pointer, "REA", aux.getCategory()));
                            pointer = pointer + 1;
                            instructionStack.add(new Instruction(pointer, "STR",
                                    Integer.toString(Integer.parseInt(objIdentifierRule10.getAttrOne()) + integerConstant12 - 1)));
                            pointer = pointer + 1;
                        } else {
                            this.addError(identifierRule10, "Indexed variable identifier requires index");
                        }
                    }
                } else {
                    this.addError(identifierRule10, "Undeclared identifier or program identifier, constant or enumerated type");
                }
                break;
            default:
                break;
        }


        System.out.println("regra 11");
        printInstructions();
    }

    public void rule12(Token token) {
        String identifier = token.image;

        integerConstant12 = Integer.parseInt(identifier);
        indexedVariable = true;
        System.out.println(" ACHOU UMA  COM INDEX " + integerConstant12);
        System.out.println("regra 12");
        printInstructions();
    }

    public void rule13() {
        if (context.equals(AS_VARIABLE)) {
            type = 1;
        } else {
            type = 5;
        }
        System.out.println("regra 13");
        printInstructions();
    }

    public void rule14() {
        if (context.equals(AS_VARIABLE)) {
            type = 2;
        } else {
            type = 6;
        }
        System.out.println("regra 14");
        printInstructions();
    }

    public void rule15() {
        if (context.equals(AS_VARIABLE)) {
            type = 3;
        } else {
            type = 7;
        }
        System.out.println("regra 15");
        printInstructions();
    }

    public void rule16() {
        if (context.equals(AS_VARIABLE)) {
            type = 4;
        } else {
            error.add("Error: Invalid type for constant");
            haveError = true;
        }
        System.out.println("regra 16");
        printInstructions();
    }

    public void rule17() {
        if (context.equals(AS_VARIABLE)) {
            type = 1;
        } else {
            error.add("Error: Invalid type for constant");
            haveError = true;
        }
        System.out.println("regra 17");
        printInstructions();
    }

    public void rule18() {
        context = ASSIGNMENT;
        System.out.println("regra 18");

    }

    public void rule19() {
        for (String attribute : attributeList) {
            instructionStack.add(new Instruction(pointer, "STR", attribute));
            pointer += 1;
        }
        attributeList = new ArrayList<>();
        System.out.println("regra 19");
        printInstructions();
    }

    public void rule20() {
        context = INPUT;
        System.out.println("regra 20");
        printInstructions();
    }

    public void rule21() {
        output = WRITE_ALL_THIS;
        System.out.println("regra 21");
        printInstructions();
    }

    public void rule22() {
        output = WRITE_THIS;
        System.out.println("regra 22");
        printInstructions();
    }

    public void rule23() {
        instructionStack.add(new Instruction(pointer, "WRT", "0"));
        pointer = pointer + 1;
        System.out.println("regra 23");
        printInstructions();
    }

    public void rule24(Token token) {
        String identifier = token.image;
        Symbol objIdentifier = recoverSymbol(identifier);
        if (isSymbolPresent(identifier) && (objIdentifier != null && !objIdentifier.getCategory().equals("0"))) {
            indexedVariable = false;
            identifierRule24 = token;
        } else {
            this.identifierRule24Error = token;
            this.addError(token, "Undeclared identifier or program identifier or enumerated type");
        }
        System.out.println("regra 24");
        printInstructions();
    }

    public void rule25() {
        Symbol objIdentifierRule24;
        if (identifierRule24 != null) {
            objIdentifierRule24 = recoverSymbol(identifierRule24.image);
        } else {
            objIdentifierRule24 = null;
        }
        if (!indexedVariable) {
            if (objIdentifierRule24 != null && objIdentifierRule24.getAttrTwo().equals("-")) {
                if (output.equals(WRITE_ALL_THIS)) {
                    instructionStack.add(new Instruction(pointer, "LDS", objIdentifierRule24.getIdentifier().concat("=")));
                    pointer = pointer + 1;
                    instructionStack.add(new Instruction(pointer, "WRT", "0"));
                    pointer = pointer + 1;
                }
                instructionStack.add(new Instruction(pointer, "LDV", objIdentifierRule24.getAttrOne()));
                pointer = pointer + 1;
            } else {
                this.addError(identifierRule24Error, "Indexed variable identifier requires index");
            }
        } else {
            if (objIdentifierRule24 != null && !objIdentifierRule24.getAttrTwo().equals("-")) {
                if (output.equals(WRITE_ALL_THIS)) {
                    instructionStack.add(new Instruction(pointer, "LDS", objIdentifierRule24.getIdentifier().concat("=")));
                    pointer = pointer + 1;
                    instructionStack.add(new Instruction(pointer, "WRT", "0"));
                    pointer = pointer + 1;
                }
                int auxValue = Integer.parseInt(objIdentifierRule24.getAttrOne()) + integerConstant12 - 1;
                instructionStack.add(new Instruction(pointer, "LDV", String.valueOf(auxValue)));
                pointer = pointer + 1;
            } else {
                this.addError(identifierRule24Error, "Constant or non-indexed variable identifier");
            }
        }

        System.out.println("regra 25");
        printInstructions();
    }

    public void rule26(Token token) {
        String identifier = token.image;
        instructionStack.add(new Instruction(pointer, "LDI", identifier));
        pointer = pointer + 1;
        System.out.println("regra 26");
        printInstructions();
    }

    public void rule27(Token token) {
        String identifier = token.image;
        instructionStack.add(new Instruction(pointer, "LDR", identifier));
        pointer = pointer + 1;
        System.out.println("regra 27");
        printInstructions();
    }

    public void rule28(Token token) {
        String identifier = token.image;
        instructionStack.add(new Instruction(pointer, "LDS", identifier));
        pointer = pointer + 1;
        System.out.println("regra 28");
        printInstructions();
    }

    public void rule29() {
        String lastOfStack = unstack();
        if(lastOfStack != null) {
            for (Instruction instruction : instructionStack) {
                if (instruction.getPointer() == Integer.parseInt(lastOfStack)) {
                    instruction.setAddress(pointer.toString());
                    break;
                }
            }
        }
        System.out.println("regra 29");
        printInstructions();
    }

    public void rule30() {
        instructionStack.add(new Instruction(pointer, "JMF", "?"));
        stack(pointer.toString());
        pointer += 1;
        System.out.println("regra 30");
        printInstructions();
    }

    public void rule31() {
        instructionStack.add(new Instruction(pointer, "JMT", "?"));
        stack(pointer.toString());
        pointer += 1;
        System.out.println("regra 31");
        printInstructions();
    }

    public void rule32() {
        unstackLastInstructionAndUpdate();
        instructionStack.add(new Instruction(pointer, "JMP", "?"));
        stack(pointer.toString());
        pointer += 1;
        System.out.println("regra 32");
        printInstructions();
    }

    public void rule33() {
        stack(pointer.toString());
        System.out.println("regra 33");
        printInstructions();
    }

    public void rule34() {
        instructionStack.add(new Instruction(pointer, "JMF", "?"));
        pointer += 1;
        int pointerAux = pointer - 1;
        stack(Integer.toString(pointerAux));
        System.out.println("regra 34");
        printInstructions();
    }

    public void rule35() {
        unstackLastInstructionAndUpdate();
        String lastOfStack33 = unstack();
        instructionStack.add(new Instruction(pointer, "JMP", lastOfStack33));
        pointer += 1;
        System.out.println("regra 35");
        printInstructions();
    }

    public void rule36() {
        instructionStack.add(new Instruction(pointer, "EQL", "0")); // ==
        pointer = pointer + 1;
        System.out.println("regra 36");
        printInstructions();
    }

    public void rule37() {
        instructionStack.add(new Instruction(pointer, "DIF", "0")); // !=
        pointer = pointer + 1;
        System.out.println("regra 37");
        printInstructions();
    }

    public void rule38() {
        instructionStack.add(new Instruction(pointer, "SMR", "0")); // <<
        pointer = pointer + 1;
        System.out.println("regra 38");
        printInstructions();
    }

    public void rule39() {
        instructionStack.add(new Instruction(pointer, "BGR", "0")); // >>
        pointer = pointer + 1;
        System.out.println("regra 39");
        printInstructions();
    }

    public void rule40() {
        instructionStack.add(new Instruction(pointer, "SME", "0")); // <<=
        pointer = pointer + 1;
        System.out.println("regra 40");
        printInstructions();
    }

    public void rule41() {
        instructionStack.add(new Instruction(pointer, "BGE", "0")); // >>=
        pointer = pointer + 1;
        System.out.println("regra 41");
        printInstructions();
    }

    public void rule42() {
        instructionStack.add(new Instruction(pointer, "ADD", "0")); // +
        pointer = pointer + 1;
        System.out.println("regra 42");
        printInstructions();
    }

    public void rule43() {
        instructionStack.add(new Instruction(pointer, "SUB", "0")); // -
        pointer = pointer + 1;
        System.out.println("regra 43");
        printInstructions();
    }

    public void rule44() {
        instructionStack.add(new Instruction(pointer, "OR", "0")); // |
        pointer = pointer + 1;
        System.out.println("regra 44");
        printInstructions();
    }

    public void rule45() {
        instructionStack.add(new Instruction(pointer, "MUL", "0")); // *
        pointer = pointer + 1;
        System.out.println("regra 45");
        printInstructions();
    }

    public void rule46() {
        instructionStack.add(new Instruction(pointer, "DIV", "0")); // /
        pointer = pointer + 1;
        System.out.println("regra 46");
        printInstructions();
    }

    public void rule47() {
        instructionStack.add(new Instruction(pointer, "DVD", "0")); // %
        pointer = pointer + 1;
        System.out.println("regra 47");
        printInstructions();
    }

    public void rule48() {
        instructionStack.add(new Instruction(pointer, "MOD", "0")); // %%
        pointer = pointer + 1;
        System.out.println("regra 48");
        printInstructions();
    }

    public void rule49() {
        instructionStack.add(new Instruction(pointer, "AND", "0")); // &
        pointer = pointer + 1;
        System.out.println("regra 49");
        printInstructions();
    }

    public void rule50() {
        instructionStack.add(new Instruction(pointer, "POW", "0")); // **
        pointer = pointer + 1;
        System.out.println("regra 50");
        printInstructions();
    }

    public void rule51() {
        Symbol objIdentifierRule24 = recoverSymbol(identifierRule24 != null ? identifierRule24.image : null);
        if (!haveError) {
            if (!indexedVariable) {
                if (objIdentifierRule24 != null && objIdentifierRule24.getAttrTwo().equals("-")) {
                    System.out.println("atributo kkkkk " +  objIdentifierRule24.getAttrOne());

                    instructionStack.add(new Instruction(pointer, "LDV", objIdentifierRule24.getAttrOne()));
                    pointer = pointer + 1;
                } else {
                    this.addError(identifierRule24, "Indexed variable identifier requires index");
                }
            } else {
                if (objIdentifierRule24 != null && !objIdentifierRule24.getAttrTwo().equals("-")) {
                    int auxValue = Integer.parseInt(objIdentifierRule24.getAttrOne()) + integerConstant12 - 1;
                    instructionStack.add(new Instruction(pointer, "LDV", String.valueOf(auxValue)));
                    pointer = pointer + 1;
                } else {
                    this.addError(identifierRule24, "'" + identifierRule24.image + "' is Identifier of constant or indexed variable");
                }
            }
        }
        System.out.println("regra 51");
        printInstructions();
    }

    public void rule52() {
        instructionStack.add(new Instruction(pointer, "LDB", "true"));
        pointer = pointer + 1;
        System.out.println("regra 52");
        printInstructions();
    }

    public void rule53() {
        instructionStack.add(new Instruction(pointer, "LDB", "false"));
        pointer = pointer + 1;
        System.out.println("regra 53");
        printInstructions();
    }

    public void rule54() {
        instructionStack.add(new Instruction(pointer, "NOT", "0"));
        pointer = pointer + 1;
        System.out.println("regra 54");
        printInstructions();
    }

    public void addError(Token token, String message) {
        boolean errorExists = false;
        for (Token errorToken : existingErrors) {
            if (Objects.equals(errorToken.image, token.image) &&
                    errorToken.beginLine == token.beginLine &&
                    errorToken.beginColumn == token.beginColumn) {
                errorExists = true;
                break;
            }
        }
        if (!errorExists) {
            this.error.add("Error: " + message + " - located at [line:" + token.beginLine + "| column: " + token.endColumn + "]");
            this.existingErrors.add(token);
        }
        haveError = true;
    }


    public void printInstructions() {
        System.out.println("Pointer: "  + pointer);
        System.out.println("context: " + context);
        System.out.println("vp:" + VP);
        System.out.println("vI:" + VI);
        System.out.println("vt:" + VT);
        System.out.println("tvi:" + TVI);
        System.out.println("type:" + type);
        System.out.println("Instruction Stack: ");
        for (Instruction inst : instructionStack) {
            System.out.println(inst.getPointer() + ", " + inst.getCode() + ", " + inst.getAddress());
        }
        System.out.println("Symbol Table: ");
        for (Symbol symbol : symbolTable) {
            System.out.println(symbol.getIdentifier() + ", " + symbol.getCategory() + ", " + symbol.getAttrOne() + ", " + symbol.getAttrTwo());
        }

        System.out.println("Stack Table: ");
        for (String string : deviationStack) {
            System.out.println(string + ", ");
        }
        if (haveError) {
            System.out.println("Errors: ");
            for (String error : error) {
                System.out.println(error + "\n");
            }
        }
        System.out.println("\n");
    }

}

