package virtualMachine;

import compiler.CompilerApplication;
import parser.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VirtualMachine {

    private ArrayList<Stack> stack = this.initialiseStack();
    private int pointer = 1;
    CompilerApplication screen;
    private int top = 0;
    private String error = "";
    private boolean stopped;

    public VirtualMachine(CompilerApplication screen) {
        this.screen = screen;
    }

    public void reset() {
        this.top = 0;
        this.pointer = 1;
        this.stack = this.initialiseStack();
        this.screen.eraseRuntimeInput();
    }

    private ArrayList<Stack> initialiseStack() {
        ArrayList<Stack> stackList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Stack stack = new Stack("", "");
            stackList.add(stack);
        }
        return stackList;
    }

    public void run(List<Instruction> instructionTable) {
        this.stopped = false;
        new Thread(() -> {
            while (!this.stopped) {
                synchronized (this) {
                    if (this.stopped) {
                        break;
                    }
                }
                this.chooseInstruction(instructionTable.get(this.pointer - 1));
            }
            if (!Objects.equals(this.error, "")) {
                this.screen.appendToRuntimeTerminal(this.error);
            }
            this.screen.appendToRuntimeTerminal("--- Program stopped ---");
            this.screen.programStopped();
        }).start();

    }

    private void printStack() {
        for (Stack stack : this.stack) {
            if (!Objects.equals(stack.getType(), "")) {
                System.out.println(stack.getData() + " ");
            }
        }
    }

    private void chooseInstruction(Instruction instruction) {
        System.out.println("========= ANTES ===========");
        System.out.println("STACK: ");
        this.printStack();
        System.out.println("Pointer: " + this.pointer + " Top: " + this.top);
        System.out.println("INSTRUCTION: ");
        System.out.println(instruction.getCode() + " " + instruction.getAddress());
        System.out.println();

        switch (instruction.getCode()) {
            case "ADD":
                this.add();
                break;
            case "ALB":
                this.alb(Integer.parseInt(instruction.getAddress()));
                break;
            case "ALI":
                this.ali(Integer.parseInt(instruction.getAddress()));
                break;
            case "ALR":
                this.alr(Integer.parseInt(instruction.getAddress()));
                break;
            case "ALS":
                this.als(Integer.parseInt(instruction.getAddress()));
                break;
            case "AND":
                this.and();
                break;
            case "BGE":
                this.bge();
                break;
            case "BGR":
                this.bgr();
                break;
            case "DIF":
                this.dif();
                break;
            case "DIV":
                this.div();
                break;
            case "DVD":
                this.dvd();
                break;
            case "POW":
                this.pow();
                break;
            case "MOD":
                this.mod();
                break;
            case "EQL":
                this.eql();
                break;
            case "JMF":
                this.jmf(Integer.parseInt(instruction.getAddress()));
                break;
            case "JMP":
                this.jmp(Integer.parseInt(instruction.getAddress()));
                break;
            case "JMT":
                this.jmt(Integer.parseInt(instruction.getAddress()));
                break;
            case "LDV":
                this.ldv(Integer.parseInt(instruction.getAddress()));
                break;
            case "LDB":
                this.ldb(String.valueOf(instruction.getAddress()));
                break;
            case "LDI":
                this.ldi(String.valueOf(instruction.getAddress()));
                break;
            case "LDR":
                this.ldr(String.valueOf(instruction.getAddress()));
                break;
            case "LDS":
                this.lds(String.valueOf(instruction.getAddress()));
                break;
            case "MUL":
                this.mul();
                break;
            case "NOT":
                this.not();
                break;
            case "OR":
                this.or();
                break;
            case "REA":
                this.rea(String.valueOf(instruction.getAddress()));
                break;
            case "SME":
                this.sme();
                break;
            case "SMR":
                this.smr();
                break;
            case "STR":
                this.str(Integer.parseInt(instruction.getAddress()));
                break;
            case "STP":
                this.stp();
                break;
            case "SUB":
                this.sub();
                break;
            case "WRT":
                this.wrt();
                break;
            case "STC":
                this.stc(Integer.parseInt(instruction.getAddress()));
                break;

        }
        System.out.println("========= DEPOIS ===========");
        System.out.println("STACK: ");
        this.printStack();
        System.out.println("Pointer: " + this.pointer + " Top: " + this.top);
        System.out.println("INSTRUCTION: ");
        System.out.println(instruction.getCode() + " " + instruction.getAddress());
        System.out.println();
    }


    private Stack getSumByType(Stack left, Stack right) {
        String type;
        String leftType = left.getType();
        String rightType = right.getType();

        if (Objects.equals(leftType, "real") && Objects.equals(rightType, "real")) {
            type = "real";
        } else if (Objects.equals(leftType, "real") && Objects.equals(rightType, "integer")) {
            type = "realInteger";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "real")) {
            type = "integerReal";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "integer")) {
            type = "integer";
        } else {
            this.error = "Error: The variable must be a number";
            this.stopped = true;
            return null;
        }

        Number result;

        switch (type) {
            case "real":
                result = Float.parseFloat(left.getData()) + Float.parseFloat(right.getData());
                break;
            case "realInteger":
                result = Float.parseFloat(left.getData()) + Integer.parseInt(right.getData());
                break;
            case "integerReal":
                result = Integer.parseInt(left.getData()) + Float.parseFloat(right.getData());
                break;
            case "integer":
                result = Integer.parseInt(left.getData()) + Integer.parseInt(right.getData());
                break;
            default:
                return null;
        }

        return new Stack(String.valueOf(result), type);
    }

    private void add() {
        Stack data = this.getSumByType(this.stack.get(this.top - 1), this.stack.get(this.top));
        if (data != null) {
            this.stack.set(this.top - 1, data);
            this.top -= 1;
            this.pointer += 1;
        }
    }

    private void alb(int displacement) {
        Stack data = new Stack("false", "logic");
        for (int i = this.top + 1; i < this.top + displacement; i++) {
            this.stack.set(i, data);
        }
        this.top += displacement;
        this.pointer += 1;
    }

    private void ali(int displacement) {
        Stack data = new Stack("0", "integer");
        for (int i = this.top + 1; i <= this.top + displacement; i++) {
            this.stack.set(i, data);
        }
        this.top += displacement;
        this.pointer += 1;
    }

    private void alr(int displacement) {
        Stack data = new Stack("0.0", "real");
        for (int i = this.top + 1; i <= this.top + displacement; i++) {
            this.stack.set(i, data);
        }
        this.top += displacement;
        this.pointer += 1;
    }

    private void als(int displacement) {
        Stack data = new Stack("", "string");
        for (int i = this.top + 1; i <= this.top + displacement; i++) {
            this.stack.set(i, data);
        }
        this.top += displacement;
        this.pointer += 1;
    }

    private void and() {
        Stack data = new Stack(String.valueOf(Boolean.parseBoolean(this.stack.get(this.top - 1).getData()) && Boolean.parseBoolean(this.stack.get(this.top).getData())), "logic");
        this.stack.set(this.top - 1, data);
        this.top -= 1;
        this.pointer += 1;
    }

    private void bge() {
        Stack data = new Stack(String.valueOf(Float.parseFloat(this.stack.get(this.top - 1).getData()) >= Float.parseFloat(this.stack.get(this.top).getData())), "logic");
        this.stack.set(this.top - 1, data);
        this.top -= 1;
        this.pointer += 1;
    }

    private void bgr() {
        Stack data = new Stack(String.valueOf(Float.parseFloat(this.stack.get(this.top - 1).getData()) > Float.parseFloat(this.stack.get(this.top).getData())), "logic");
        this.stack.set(this.top - 1, data);
        this.top -= 1;
        this.pointer += 1;
    }

    private Stack getDifByType(Stack left, Stack right) {
        String type;
        String leftType = left.getType();
        String rightType = right.getType();

        if (Objects.equals(leftType, "real") || Objects.equals(rightType, "real")) {
            type = "real";
        } else {
            if (Objects.equals(leftType, "integer") || Objects.equals(rightType, "integer")) {
                type = "integer";
            } else {
                type = "string";
            }
        }

        switch (type) {
            case "real":
                return new Stack(String.valueOf(Float.parseFloat(left.getData()) != Float.parseFloat(right.getData())), "real");
            case "integer":
                return new Stack(String.valueOf(Integer.parseInt(left.getData()) != Integer.parseInt(right.getData())), "integer");
            case "string":
                return new Stack(String.valueOf(!Objects.equals(left.getData(), right.getData())), "string");
        }
        return null;
    }

    private void dif() {
        this.stack.set(this.top - 1, getDifByType(this.stack.get(this.top - 1), this.stack.get(this.top)));
        this.top -= 1;
        this.pointer += 1;
    }


    private Stack getDivByType(Stack left, Stack right) {
        String type;
        String leftType = left.getType();
        String rightType = right.getType();

        if (Objects.equals(leftType, "real") && Objects.equals(rightType, "real")) {
            type = "real";
        } else if (Objects.equals(leftType, "real") && Objects.equals(rightType, "integer")) {
            type = "realInteger";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "real")) {
            type = "integerReal";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "integer")) {
            type = "integer";
        } else {
            this.error = "Error: The variable must be a number";
            this.stopped = true;
            return null;
        }

        Number result;

        switch (type) {
            case "real":
                result = Float.parseFloat(left.getData()) / Float.parseFloat(right.getData());
                break;
            case "realInteger":
                result = Float.parseFloat(left.getData()) / Integer.parseInt(right.getData());
                break;
            case "integerReal":
                result = Integer.parseInt(left.getData()) / Float.parseFloat(right.getData());
                break;
            case "integer":
                result = Integer.parseInt(left.getData()) / Integer.parseInt(right.getData());
                break;
            default:
                return null;
        }

        return new Stack(String.valueOf(result), type);
    }


    private void div() {
        String type = Objects.equals(this.stack.get(this.top).getType(), "real") ? "real" : "integer";
        if (type.equals("real")) {
            if (Float.parseFloat(this.stack.get(this.top).getData()) == 0.0) {
                this.error = "Error: Impossible to divide by 0.0";
                this.stopped = true;
                return;
            }
        } else {
            if (Integer.parseInt(this.stack.get(this.top).getData()) == 0) {
                this.error = "Error: Impossible to divide by 0";
                this.stopped = true;
                return;
            }
        }

        this.stack.set(this.top - 1, this.getDivByType(this.stack.get(this.top - 1), this.stack.get(this.top)));
        this.top -= 1;
        this.pointer += 1;
    }


    private Stack getModByType(Stack left, Stack right) {
        String type;
        String leftType = left.getType();
        String rightType = right.getType();

        if (Objects.equals(leftType, "real") && Objects.equals(rightType, "real")) {
            type = "real";
        } else if (Objects.equals(leftType, "real") && Objects.equals(rightType, "integer")) {
            type = "realInteger";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "real")) {
            type = "integerReal";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "integer")) {
            type = "integer";
        } else {
            this.error = "Error: The variable must be a number";
            this.stopped = true;
            return null;
        }

        Number result;

        switch (type) {
            case "real":
                result = Float.parseFloat(left.getData()) % Float.parseFloat(right.getData());
                break;
            case "realInteger":
                result = Float.parseFloat(left.getData()) % Integer.parseInt(right.getData());
                break;
            case "integerReal":
                result = Integer.parseInt(left.getData()) % Float.parseFloat(right.getData());
                break;
            case "integer":
                result = Integer.parseInt(left.getData()) % Integer.parseInt(right.getData());
                break;
            default:
                return null;
        }

        return new Stack(String.valueOf(result), type);
    }

    private void mod() {
        this.stack.set(this.top - 1, this.getModByType(this.stack.get(this.top - 1), this.stack.get(this.top)));
        this.top -= 1;
        this.pointer += 1;
    }

    private Stack getDvdByType(Stack left, Stack right) {
        String type;
        String leftType = left.getType();
        String rightType = right.getType();

        if (Objects.equals(leftType, "real") && Objects.equals(rightType, "real")) {
            type = "real";
        } else if (Objects.equals(leftType, "real") && Objects.equals(rightType, "integer")) {
            type = "realInteger";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "real")) {
            type = "integerReal";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "integer")) {
            type = "integer";
        } else {
            this.error = "Error: The variable must be a number";
            this.stopped = true;
            return null;
        }

        Number result;

        switch (type) {
            case "real":
                result = Math.round(Float.parseFloat(left.getData()) / Float.parseFloat(right.getData()));
                break;
            case "realInteger":
                result = Math.round(Float.parseFloat(left.getData()) / Integer.parseInt(right.getData()));
                break;
            case "integerReal":
                result = Math.round(Integer.parseInt(left.getData()) / Float.parseFloat(right.getData()));
                break;
            case "integer":
                result = Math.round((float) Integer.parseInt(left.getData()) / Integer.parseInt(right.getData()));
                break;
            default:
                return null;
        }

        return new Stack(String.valueOf(result), type);
    }

    private void dvd() {
        this.stack.set(this.top - 1, this.getDvdByType(this.stack.get(this.top - 1), this.stack.get(this.top)));
        this.top -= 1;
        this.pointer += 1;
    }

    private Stack getPowByType(Stack left, Stack right) {
        String type;
        String leftType = left.getType();
        String rightType = right.getType();

        if (Objects.equals(leftType, "real") && Objects.equals(rightType, "real")) {
            type = "real";
        } else if (Objects.equals(leftType, "real") && Objects.equals(rightType, "integer")) {
            type = "realInteger";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "real")) {
            type = "integerReal";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "integer")) {
            type = "integer";
        } else {
            this.error = "Error: The variable must be a number";
            this.stopped = true;
            return null;
        }

        Number result;

        switch (type) {
            case "real":
                result = Math.pow(Float.parseFloat(left.getData()), Float.parseFloat(right.getData()));
                break;
            case "realInteger":
                result = Math.pow(Float.parseFloat(left.getData()), Integer.parseInt(right.getData()));
                break;
            case "integerReal":
                result = Math.pow(Integer.parseInt(left.getData()), Float.parseFloat(right.getData()));
                break;
            case "integer":
                result = Math.pow(Integer.parseInt(left.getData()), Integer.parseInt(right.getData()));
                break;
            default:
                return null;
        }

        return new Stack(String.valueOf(result), type);
    }


    private void pow() {
        this.stack.set(this.top - 1, this.getPowByType(this.stack.get(this.top - 1), this.stack.get(this.top)));
        this.top -= 1;
        this.pointer += 1;
    }

    private Stack getEqlByType(Stack left, Stack right) {
        String type;
        String leftType = left.getType();
        String rightType = right.getType();

        if (Objects.equals(leftType, "real") || Objects.equals(rightType, "real")) {
            type = "real";
        } else {
            if (Objects.equals(leftType, "integer") || Objects.equals(rightType, "integer")) {
                type = "integer";
            } else {
                type = "string";

            }
        }

        switch (type) {
            case "real":
                return new Stack(String.valueOf(Float.parseFloat(left.getData()) == Float.parseFloat(right.getData())), "real");
            case "integer":
                return new Stack(String.valueOf(Integer.parseInt(left.getData()) == Integer.parseInt(right.getData())), "integer");
            case "string":
                return new Stack(String.valueOf(Objects.equals(left.getData(), right.getData())), "string");
        }
        return null;
    }

    private void eql() {
        this.stack.set(this.top - 1, getEqlByType(this.stack.get(this.top - 1), this.stack.get(this.top)));
        this.top -= 1;
        this.pointer += 1;
    }

    private void jmf(int address) {
        if (Objects.equals(this.stack.get(this.top).getData(), "false")) {
            this.pointer = address;
        } else {
            this.pointer += 1;
        }
        this.top -= 1;
    }

    private void jmp(int address) {
        this.pointer = address;
    }

    private void jmt(int address) {
        if (Objects.equals(this.stack.get(this.top).getData(), "true")) {
            this.pointer = address;
        } else {
            this.pointer += 1;
        }
        this.top -= 1;
    }

    private void ldv(int address) {
        this.top += 1;
        this.stack.set(this.top, this.stack.get(address));
        this.pointer += 1;
    }

    private Stack getLoadType(String instruction) {
        try {
            int constant = Integer.parseInt(instruction);
            return new Stack(String.valueOf(constant), "integer");
        } catch (NumberFormatException e1) {
            try {
                float constant = Float.parseFloat(instruction);
                return new Stack(String.valueOf(constant), "real");
            } catch (NumberFormatException e2) {
                if ("true".equalsIgnoreCase(instruction) || "false".equalsIgnoreCase(instruction)) {
                    return new Stack(instruction, "logic");
                } else {
                    return new Stack(instruction, "string");
                }
            }
        }

    }

    private void ldb(String constant) {
        Stack data = this.getLoadType(constant);
        if (!Objects.equals(data.getType(), "logic")) {
            this.error = "Error: The variable is of logical type";
            this.stopped = true;
            return;
        }
        this.top += 1;
        this.stack.set(this.top, data);
        this.pointer += 1;
    }

    private void ldi(String constant) {
        Stack data = this.getLoadType(constant);
        if (!Objects.equals(data.getType(), "integer")) {
            this.error = "Error: The variable is of the integer type";
            this.stopped = true;
            return;
        }
        this.top += 1;
        this.stack.set(this.top, data);
        this.pointer += 1;
    }

    private void ldr(String constant) {
        Stack data = this.getLoadType(constant);
        if (!Objects.equals(data.getType(), "real")) {
            this.error = "Error: The variable is of real type";
            this.stopped = true;
            return;
        }
        this.top += 1;
        this.stack.set(this.top, data);
        this.pointer += 1;
    }

    private void lds(String constant) {
        Stack data = this.getLoadType(constant);
        if (!Objects.equals(data.getType(), "string")) {
            this.error = "Error: The variable is of string type";
            this.stopped = true;
            return;
        }
        this.top += 1;
        this.stack.set(this.top, data);
        this.pointer += 1;
    }


    private Stack getMulByType(Stack left, Stack right) {
        String type;
        String leftType = left.getType();
        String rightType = right.getType();

        if (Objects.equals(leftType, "real") && Objects.equals(rightType, "real")) {
            type = "real";
        } else if (Objects.equals(leftType, "real") && Objects.equals(rightType, "integer")) {
            type = "realInteger";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "real")) {
            type = "integerReal";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "integer")) {
            type = "integer";
        } else {
            this.error = "Error: The variable must be a number";
            this.stopped = true;
            return null;
        }

        Number result;

        switch (type) {
            case "real":
                result = Float.parseFloat(left.getData()) * Float.parseFloat(right.getData());
                break;
            case "realInteger":
                result = Float.parseFloat(left.getData()) * Integer.parseInt(right.getData());
                break;
            case "integerReal":
                result = Integer.parseInt(left.getData()) * Float.parseFloat(right.getData());
                break;
            case "integer":
                result = Integer.parseInt(left.getData()) * Integer.parseInt(right.getData());
                break;
            default:
                return null;
        }

        return new Stack(String.valueOf(result), type);
    }

    private void mul() {
        Stack data = this.getMulByType(this.stack.get(this.top - 1), this.stack.get(this.top));
        if (data != null) {
            this.stack.set(this.top - 1, data);
            this.top -= 1;
            this.pointer += 1;
        }
    }

    private void not() {
        Stack notTop = new Stack(String.valueOf(!Boolean.parseBoolean(this.stack.get(this.top).getData())), "logic");
        this.stack.set(this.top, notTop);
        this.pointer += 1;
    }

    private void or() {
        Stack data = new Stack(String.valueOf(Boolean.parseBoolean(this.stack.get(this.top - 1).getData()) || Boolean.parseBoolean(this.stack.get(this.top).getData())), "logic");
        this.stack.set(this.top - 1, data);
        this.top -= 1;
        this.pointer += 1;
    }


    private String getInputType(String data, String typeNumber) {
        if (typeNumber.equals("3")) {
            return "3";
        }
        try {
            int constant = Integer.parseInt(data);
            return "1";
        } catch (NumberFormatException e1) {
            try {
                float constant = Float.parseFloat(data);
                return "2";
            } catch (NumberFormatException e2) {
                return "3";
            }
        }
    }

    private String getTypeByNumber(String number) {
        switch (number) {
            case "1":
            case "5":
                return "integer";
            case "2":
            case "6":
                return "real";
            case "3":
            case "7":
                return "string";
            case "4":
                return "logic";
        }
        return "";
    }

    private void rea(String typeNumber) {
        this.screen.getInputAsync().thenAccept(userInput -> {
            this.top += 1;
            this.pointer += 1;
            String type = getInputType(userInput, typeNumber);
            String typeAux = getTypeByNumber(type);
            this.stack.set(this.top, new Stack(userInput, typeAux));
            if (!Objects.equals(type, typeNumber)) {
                this.error = "Error: The element read must be of type " + getTypeByNumber(typeNumber);
                this.stopped = true;
                return;
            }
            this.screen.eraseInputRuntime();
            this.screen.setLastInput("");
        }).join();
    }

    private void sme() {
        Stack data = new Stack(String.valueOf(Float.parseFloat(this.stack.get(this.top - 1).getData()) <= Float.parseFloat(this.stack.get(this.top).getData())), "logic");
        this.stack.set(this.top - 1, data);
        this.top -= 1;
        this.pointer += 1;
    }

    private void smr() {
        Stack data = new Stack(String.valueOf(Float.parseFloat(this.stack.get(this.top - 1).getData()) < Float.parseFloat(this.stack.get(this.top).getData())), "logic");
        this.stack.set(this.top - 1, data);
        this.top -= 1;
        this.pointer += 1;
    }

    private void str(int address) {
        this.stack.set(address, this.stack.get(this.top));
        this.top -= 1;
        this.pointer += 1;
    }

    private void stp() {
        this.stopped = true;
    }

    private Stack getSubByType(Stack left, Stack right) {
        String type;
        String leftType = left.getType();
        String rightType = right.getType();

        if (Objects.equals(leftType, "real") && Objects.equals(rightType, "real")) {
            type = "real";
        } else if (Objects.equals(leftType, "real") && Objects.equals(rightType, "integer")) {
            type = "realInteger";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "real")) {
            type = "integerReal";
        } else if (Objects.equals(leftType, "integer") && Objects.equals(rightType, "integer")) {
            type = "integer";
        } else {
            this.error = "Error: The variable must be a number";
            this.stopped = true;
            return null;
        }

        Number result;

        switch (type) {
            case "real":
                result = Float.parseFloat(left.getData()) - Float.parseFloat(right.getData());
                break;
            case "realInteger":
                result = Float.parseFloat(left.getData()) - Integer.parseInt(right.getData());
                break;
            case "integerReal":
                result = Integer.parseInt(left.getData()) - Float.parseFloat(right.getData());
                break;
            case "integer":
                result = Integer.parseInt(left.getData()) - Integer.parseInt(right.getData());
                break;
            default:
                return null;
        }

        return new Stack(String.valueOf(result), type);
    }

    private void sub() {
        Stack data = this.getSubByType(this.stack.get(this.top - 1), this.stack.get(this.top));
        if (data != null) {
            this.stack.set(this.top - 1, data);
            this.top -= 1;
            this.pointer += 1;
        }

    }

    private void wrt() {
        this.screen.appendToRuntimeTerminal(this.stack.get(this.top).getData());
        this.top -= 1;
        this.pointer += 1;
    }


    private void stc(int displacement) {
        for (int i = this.top - displacement; i <= this.top - 1; i++) {
            this.stack.set(i, this.stack.get(this.top));
        }
        this.top -= 1;
        this.pointer += 1;
    }


}
