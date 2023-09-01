package LexicalAnalysis;/* Generated By:JavaCC: Do not edit this line. LexicalAnalysisConstants.java */

/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface LexicalAnalysisConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int DO = 11;
  /** RegularExpression Id. */
  int IS = 12;
  /** RegularExpression Id. */
  int AS = 13;
  /** RegularExpression Id. */
  int AND = 14;
  /** RegularExpression Id. */
  int ALL = 15;
  /** RegularExpression Id. */
  int THIS = 16;
  /** RegularExpression Id. */
  int BODY = 17;
  /** RegularExpression Id. */
  int TYPE = 18;
  /** RegularExpression Id. */
  int REAL = 19;
  /** RegularExpression Id. */
  int STRING = 20;
  /** RegularExpression Id. */
  int LOGIC = 21;
  /** RegularExpression Id. */
  int ENUM = 22;
  /** RegularExpression Id. */
  int READ = 23;
  /** RegularExpression Id. */
  int WRITE = 24;
  /** RegularExpression Id. */
  int REPEAT = 25;
  /** RegularExpression Id. */
  int VARIABLE = 26;
  /** RegularExpression Id. */
  int CONSTANT = 27;
  /** RegularExpression Id. */
  int RESULT = 28;
  /** RegularExpression Id. */
  int INTEGER = 29;
  /** RegularExpression Id. */
  int AVALIATE = 30;
  /** RegularExpression Id. */
  int DESCRIPTION = 31;
  /** RegularExpression Id. */
  int DECLARATION = 32;
  /** RegularExpression Id. */
  int DESIGNATE = 33;
  /** RegularExpression Id. */
  int TRUE = 34;
  /** RegularExpression Id. */
  int UNTRUE = 35;
  /** RegularExpression Id. */
  int SPECIAL_SYMBOLS = 36;
  /** RegularExpression Id. */
  int IDENTIFIER = 37;
  /** RegularExpression Id. */
  int LITERAL_CONSTANT = 38;
  /** RegularExpression Id. */
  int INTEGER_NUMERICAL_CONSTANT = 39;
  /** RegularExpression Id. */
  int REAL_NUMERICAL_CONSTANT = 40;
  /** RegularExpression Id. */
  int RESERVED_WORD = 41;
  /** RegularExpression Id. */
  int DIGIT = 42;
  /** RegularExpression Id. */
  int SYMBOL = 43;
  /** RegularExpression Id. */
  int MINUS = 44;
  /** RegularExpression Id. */
  int SINGLE_QUOTE = 45;
  /** RegularExpression Id. */
  int DOUBLE_QUOTE = 46;
  /** RegularExpression Id. */
  int LETTER = 47;
  /** RegularExpression Id. */
  int SMALL_LETTER = 48;
  /** RegularExpression Id. */
  int CAPITAL_LETTER = 49;
  /** RegularExpression Id. */
  int ASCII = 50;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int multilinecomment = 1;
  /** Lexical state. */
  int singlelinecomment = 2;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"/*\"",
    "\"//\"",
    "\"*/\"",
    "<token of kind 8>",
    "<token of kind 9>",
    "<token of kind 10>",
    "\"do\"",
    "\"is\"",
    "\"as\"",
    "\"and\"",
    "\"all\"",
    "\"this\"",
    "\"body\"",
    "\"type\"",
    "\"real\"",
    "\"string\"",
    "\"logic\"",
    "\"enum\"",
    "\"read\"",
    "\"write\"",
    "\"repeat\"",
    "\"variable\"",
    "\"constant\"",
    "\"result\"",
    "\"integer\"",
    "\"avaliate\"",
    "\"description\"",
    "\"declaration\"",
    "\"designate\"",
    "\"true\"",
    "\"untrue\"",
    "<SPECIAL_SYMBOLS>",
    "<IDENTIFIER>",
    "<LITERAL_CONSTANT>",
    "<INTEGER_NUMERICAL_CONSTANT>",
    "<REAL_NUMERICAL_CONSTANT>",
    "<RESERVED_WORD>",
    "<DIGIT>",
    "<SYMBOL>",
    "<MINUS>",
    "\"\\\'\"",
    "\"\\\"\"",
    "<LETTER>",
    "<SMALL_LETTER>",
    "<CAPITAL_LETTER>",
    "<ASCII>",
  };

}
