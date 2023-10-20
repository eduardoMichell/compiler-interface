package parser;

import parser.LangParser;
import parser.RecoverySet;


public class First {
    static public final RecoverySet main = new RecoverySet();
    static public final RecoverySet body = new RecoverySet();
    static public final RecoverySet enumDeclarations = new RecoverySet();
    static public final RecoverySet commandList = new RecoverySet();
    static public final RecoverySet end_of_file = new RecoverySet();
    static public final RecoverySet avaliate = new RecoverySet();

    static {
        main.add(LangParser.DECLARATION);
        main.add(LangParser.BODY);

        body.add(LangParser.BODY);

        commandList.add(LangParser.READ);
        commandList.add(LangParser.WRITE);
        commandList.add(LangParser.DESIGNATE);
        commandList.add(LangParser.AVALIATE);
        commandList.add(LangParser.REPEAT);

        end_of_file.add(LangParser.EOF);

        avaliate.add(LangParser.TRUE);
        avaliate.add(LangParser.UNTRUE);

        enumDeclarations.add(LangParser.DECLARATION);
    }
}
