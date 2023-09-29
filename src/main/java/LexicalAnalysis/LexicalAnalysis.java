/* LexicalAnalysis.java */
/* Generated By:JavaCC: Do not edit this line. LexicalAnalysis.java */
import java.io.*;

public class LexicalAnalysis implements LexicalAnalysisConstants {

        private String result = "";
        private boolean validLexical = true;

        public String getResult(){
            return result;
        }

        public void setResult(String result){
            this.result = result;
        }
        public boolean isValidLexical(){
            return validLexical;
        }


        private void concatMessage(Token t, String category, int categoryNumber) {
            this.result += "Token: " + t.image + " of category: (" + category + ":" + categoryNumber + ") " +
                    "located at [line:" + t.beginLine + " | column:" + t.beginColumn + "]\u005cn";
        }

        public static void main(String[] args) throws ParseException, IOException {

                  try {
                      LexicalAnalysis parser = new LexicalAnalysis(
                         new BufferedReader(new FileReader(args[0]))
                      );
                      parser.MainRule();

                      if(parser.token_source.foundLexError() != 0){
                         System.err.println(parser.token_source.foundLexError() + " Lexical Error found.");
                      } else {
                         System.err.println("Program successfully analyzed.");
                      }
                  } catch (FileNotFoundException e) {
                    System.err.println("Erro FileNotFoundException: " + e.getMessage());
                 } catch (ParseException e) {
                    System.err.println("Erro ParseException: " + e.getMessage());
                 }
        }

  void error_skipto(int kind) throws ParseException {ParseException e = generateParseException();  // generate the exception object.
  System.out.println(e.toString());  // print the error message

  Token t;
  do {
    t = getNextToken();
  } while (t.kind != kind);
  }

  final public void MainRule() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DO:
      case IS:
      case AS:
      case AND:
      case ALL:
      case THIS:
      case BODY:
      case TYPE:
      case REAL:
      case STRING:
      case LOGIC:
      case ENUM:
      case READ:
      case WRITE:
      case REPEAT:
      case VARIABLE:
      case CONSTANT:
      case RESULT:
      case INTEGER:
      case AVALIATE:
      case DESCRIPTION:
      case DECLARATION:
      case DESIGNATE:
      case TRUE:
      case UNTRUE:
      case NOT_EQUAL:
      case ATTRIBUTION:
      case EQUAL:
      case GREATERTHEN:
      case LESSTHEN:
      case LESS_EQUALS:
      case GREATER_EQUALS:
      case OR_LOGIC:
      case AND_LOGIC:
      case NEGATE_LOGIC:
      case PLUS:
      case SUBTRACTION:
      case MULTIPLY:
      case DIVISION:
      case INTEGER_DIVISION:
      case DIVISION_REMAINDER:
      case POTENCE:
      case SEMI_COLUMN:
      case DOT:
      case OPEN_KEYS:
      case CLOSE_KEYS:
      case OPEN_BOX:
      case CLOSE_BOX:
      case OPEN_BRACKETS:
      case CLOSE_BRACKETS:
      case IDENTIFIER:
      case LITERAL_CONSTANT:
      case INTEGER_NUMERICAL_CONSTANT:
      case REAL_NUMERICAL_CONSTANT:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      lexicalAnalyzer();
    }
    jj_consume_token(0);
  }

  final public void lexicalAnalyzer() throws ParseException {Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DO:{
        jj_consume_token(DO);
        break;
        }
      case IS:{
        jj_consume_token(IS);
        break;
        }
      case AS:{
        jj_consume_token(AS);
        break;
        }
      case AND:{
        jj_consume_token(AND);
        break;
        }
      case ALL:{
        jj_consume_token(ALL);
        break;
        }
      case THIS:{
        jj_consume_token(THIS);
        break;
        }
      case BODY:{
        jj_consume_token(BODY);
        break;
        }
      case TYPE:{
        jj_consume_token(TYPE);
        break;
        }
      case REAL:{
        jj_consume_token(REAL);
        break;
        }
      case STRING:{
        jj_consume_token(STRING);
        break;
        }
      case LOGIC:{
        jj_consume_token(LOGIC);
        break;
        }
      case ENUM:{
        jj_consume_token(ENUM);
        break;
        }
      case READ:{
        jj_consume_token(READ);
        break;
        }
      case WRITE:{
        jj_consume_token(WRITE);
        break;
        }
      case REPEAT:{
        jj_consume_token(REPEAT);
        break;
        }
      case VARIABLE:{
        jj_consume_token(VARIABLE);
        break;
        }
      case CONSTANT:{
        jj_consume_token(CONSTANT);
        break;
        }
      case RESULT:{
        jj_consume_token(RESULT);
        break;
        }
      case INTEGER:{
        jj_consume_token(INTEGER);
        break;
        }
      case AVALIATE:{
        jj_consume_token(AVALIATE);
        break;
        }
      case DESCRIPTION:{
        jj_consume_token(DESCRIPTION);
        break;
        }
      case DECLARATION:{
        jj_consume_token(DECLARATION);
        break;
        }
      case DESIGNATE:{
        jj_consume_token(DESIGNATE);
        break;
        }
      case TRUE:{
        jj_consume_token(TRUE);
        break;
        }
      case UNTRUE:{
        jj_consume_token(UNTRUE);
        break;
        }
      case NOT_EQUAL:{
        jj_consume_token(NOT_EQUAL);
        break;
        }
      case ATTRIBUTION:{
        jj_consume_token(ATTRIBUTION);
        break;
        }
      case EQUAL:{
        jj_consume_token(EQUAL);
        break;
        }
      case GREATERTHEN:{
        jj_consume_token(GREATERTHEN);
        break;
        }
      case LESSTHEN:{
        jj_consume_token(LESSTHEN);
        break;
        }
      case LESS_EQUALS:{
        jj_consume_token(LESS_EQUALS);
        break;
        }
      case GREATER_EQUALS:{
        jj_consume_token(GREATER_EQUALS);
        break;
        }
      case OR_LOGIC:{
        jj_consume_token(OR_LOGIC);
        break;
        }
      case AND_LOGIC:{
        jj_consume_token(AND_LOGIC);
        break;
        }
      case NEGATE_LOGIC:{
        jj_consume_token(NEGATE_LOGIC);
        break;
        }
      case PLUS:{
        jj_consume_token(PLUS);
        break;
        }
      case SUBTRACTION:{
        jj_consume_token(SUBTRACTION);
        break;
        }
      case MULTIPLY:{
        jj_consume_token(MULTIPLY);
        break;
        }
      case DIVISION:{
        jj_consume_token(DIVISION);
        break;
        }
      case INTEGER_DIVISION:{
        jj_consume_token(INTEGER_DIVISION);
        break;
        }
      case DIVISION_REMAINDER:{
        jj_consume_token(DIVISION_REMAINDER);
        break;
        }
      case POTENCE:{
        jj_consume_token(POTENCE);
        break;
        }
      case SEMI_COLUMN:{
        jj_consume_token(SEMI_COLUMN);
        break;
        }
      case DOT:{
        jj_consume_token(DOT);
        break;
        }
      case OPEN_KEYS:{
        jj_consume_token(OPEN_KEYS);
        break;
        }
      case CLOSE_KEYS:{
        jj_consume_token(CLOSE_KEYS);
        break;
        }
      case OPEN_BOX:{
        jj_consume_token(OPEN_BOX);
        break;
        }
      case CLOSE_BOX:{
        jj_consume_token(CLOSE_BOX);
        break;
        }
      case OPEN_BRACKETS:{
        jj_consume_token(OPEN_BRACKETS);
        break;
        }
      case CLOSE_BRACKETS:{
        jj_consume_token(CLOSE_BRACKETS);
        break;
        }
      case IDENTIFIER:{
        t = jj_consume_token(IDENTIFIER);
System.out.println("Identifier: " + t.image);
        break;
        }
      case INTEGER_NUMERICAL_CONSTANT:{
        t = jj_consume_token(INTEGER_NUMERICAL_CONSTANT);
System.out.println("Integer Constant: " + t.image);
        break;
        }
      case REAL_NUMERICAL_CONSTANT:{
        t = jj_consume_token(REAL_NUMERICAL_CONSTANT);
System.out.println("Real Constant: " + t.image);
        break;
        }
      case LITERAL_CONSTANT:{
        t = jj_consume_token(LITERAL_CONSTANT);
System.out.println("Literal Constant: " + t.image);
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (ParseException e) {
error_skipto(t.kind);
    }
  }

  /** Generated Token Manager. */
  public LexicalAnalysisTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[2];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
      jj_la1_init_2();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xfffff800,0xfffff800,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0xffffffff,0xffffffff,};
   }
   private static void jj_la1_init_2() {
      jj_la1_2 = new int[] {0x1,0x1,};
   }

  /** Constructor with InputStream. */
  public LexicalAnalysis(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public LexicalAnalysis(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new LexicalAnalysisTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public LexicalAnalysis(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new LexicalAnalysisTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public LexicalAnalysis(LexicalAnalysisTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(LexicalAnalysisTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[74];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 2; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 74; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
