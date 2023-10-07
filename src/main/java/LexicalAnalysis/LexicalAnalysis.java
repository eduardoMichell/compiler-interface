package LexicalAnalysis;/* LexicalAnalysis.java */
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

        private void concatMessage(Token t) {
            this.result += "Found token: (" + t.image + ") located at [line:" + t.beginLine + " | column:" + t.beginColumn + "]\u005cn";
        }

       private void concatError(Token t){
            this.result += "Invalid token (" + t.image + ") located at [line:"+t.beginLine+"| column:"+t.beginColumn+"]\u005cn";
       }

       private void concatError(Token t, String msg){
            this.result += msg + " (" + t.image + ") located at [line:"+t.beginLine+"| column:"+t.beginColumn+"]\u005cn";
       }

        public static void main(String[] args) throws ParseException, IOException {
            try {
                LexicalAnalysis parser = new LexicalAnalysis(
                     new BufferedReader(new FileReader(args[0]))
                );
                parser.MainRule();
                System.err.println("Program successfully analyzed.");
            } catch (FileNotFoundException e) {
                System.err.println("Erro FileNotFoundException: " + e.getMessage());
            } catch (ParseException e) {
                System.err.println("Erro ParseException: " + e.getMessage());
            }
        }

  final public void MainRule() throws ParseException {
    trace_call("MainRule");
    try {
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
        case NOT_EQUAL:
        case ATTRIBUTION:
        case EQUAL:
        case GREATERTHEN:
        case LESSTHEN:
        case LESS_EQUALS:
        case GREATER_EQUALS:
        case OPEN_KEYS:
        case CLOSE_KEYS:
        case OPEN_BOX:
        case CLOSE_BOX:
        case OPEN_BRACKETS:
        case CLOSE_BRACKETS:
        case SEMI_COLUMN:
        case DOT:
        case IDENTIFIER:
        case LITERAL_CONSTANT:
        case ERROR_LITERAL_CONSTANT:
        case INTEGER_NUMERICAL_CONSTANT:
        case REAL_NUMERICAL_CONSTANT:
        case ASCII:{
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
    } finally {
      trace_return("MainRule");
    }
  }

  final public void lexicalAnalyzer() throws ParseException {
    trace_call("lexicalAnalyzer");
    try {Token t;
      try {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case DO:{
          t = jj_consume_token(DO);
System.out.println("DO: " + t.image);
          break;
          }
        case IS:{
          t = jj_consume_token(IS);
System.out.println("IS: " + t.image);
          break;
          }
        case AS:{
          t = jj_consume_token(AS);
System.out.println("AS: " + t.image);
          break;
          }
        case AND:{
          t = jj_consume_token(AND);
System.out.println("AND: " + t.image);
          break;
          }
        case ALL:{
          t = jj_consume_token(ALL);
System.out.println("ALL: " + t.image);
          break;
          }
        case THIS:{
          t = jj_consume_token(THIS);
System.out.println("THIS: " + t.image);
          break;
          }
        case BODY:{
          t = jj_consume_token(BODY);
System.out.println("BODY: " + t.image);
          break;
          }
        case TYPE:{
          t = jj_consume_token(TYPE);
System.out.println("TYPE: " + t.image);
          break;
          }
        case REAL:{
          t = jj_consume_token(REAL);
System.out.println("REAL: " + t.image);
          break;
          }
        case STRING:{
          t = jj_consume_token(STRING);
System.out.println("STRING: " + t.image);
          break;
          }
        case LOGIC:{
          t = jj_consume_token(LOGIC);
System.out.println("LOGIC: " + t.image);
          break;
          }
        case ENUM:{
          t = jj_consume_token(ENUM);
System.out.println("ENUM: " + t.image);
          break;
          }
        case READ:{
          t = jj_consume_token(READ);
System.out.println("READ: " + t.image);
          break;
          }
        case WRITE:{
          t = jj_consume_token(WRITE);
System.out.println("WRITE: " + t.image);
          break;
          }
        case REPEAT:{
          t = jj_consume_token(REPEAT);
System.out.println("REPEAT: " + t.image);
          break;
          }
        case VARIABLE:{
          t = jj_consume_token(VARIABLE);
System.out.println("VARIABLE: " + t.image);
          break;
          }
        case CONSTANT:{
          t = jj_consume_token(CONSTANT);
System.out.println("CONSTANT: " + t.image);
          break;
          }
        case RESULT:{
          t = jj_consume_token(RESULT);
System.out.println("RESULT: " + t.image);
          break;
          }
        case INTEGER:{
          t = jj_consume_token(INTEGER);
System.out.println("INTEGER: " + t.image);
          break;
          }
        case AVALIATE:{
          t = jj_consume_token(AVALIATE);
System.out.println("AVALIATE: " + t.image);
          break;
          }
        case DESCRIPTION:{
          t = jj_consume_token(DESCRIPTION);
System.out.println("DESCRIPTION: " + t.image);
          break;
          }
        case DECLARATION:{
          t = jj_consume_token(DECLARATION);
System.out.println("DECLARATION: " + t.image);
          break;
          }
        case DESIGNATE:{
          t = jj_consume_token(DESIGNATE);
System.out.println("DESIGNATE: " + t.image);
          break;
          }
        case TRUE:{
          t = jj_consume_token(TRUE);
System.out.println("TRUE: " + t.image);
          break;
          }
        case UNTRUE:{
          t = jj_consume_token(UNTRUE);
System.out.println("UNTRUE: " + t.image);
          break;
          }
        case NOT_EQUAL:{
          t = jj_consume_token(NOT_EQUAL);
System.out.println("NOT_EQUAL: " + t.image);
          break;
          }
        case ATTRIBUTION:{
          t = jj_consume_token(ATTRIBUTION);
System.out.println("ATTRIBUTION: " + t.image);
          break;
          }
        case EQUAL:{
          t = jj_consume_token(EQUAL);
System.out.println("EQUAL: " + t.image);
          break;
          }
        case GREATERTHEN:{
          t = jj_consume_token(GREATERTHEN);
System.out.println("GREATERTHEN: " + t.image);
          break;
          }
        case LESSTHEN:{
          t = jj_consume_token(LESSTHEN);
System.out.println("LESSTHEN: " + t.image);
          break;
          }
        case LESS_EQUALS:{
          t = jj_consume_token(LESS_EQUALS);
System.out.println("LESS_EQUALS: " + t.image);
          break;
          }
        case GREATER_EQUALS:{
          t = jj_consume_token(GREATER_EQUALS);
System.out.println("GREATER_EQUALS: " + t.image);
          break;
          }
        case OR_LOGIC:{
          t = jj_consume_token(OR_LOGIC);
System.out.println("OR_LOGIC: " + t.image);
          break;
          }
        case AND_LOGIC:{
          t = jj_consume_token(AND_LOGIC);
System.out.println("AND_LOGIC: " + t.image);
          break;
          }
        case NEGATE_LOGIC:{
          t = jj_consume_token(NEGATE_LOGIC);
System.out.println("NEGATE_LOGIC: " + t.image);
          break;
          }
        case PLUS:{
          t = jj_consume_token(PLUS);
System.out.println("PLUS: " + t.image);
          break;
          }
        case SUBTRACTION:{
          t = jj_consume_token(SUBTRACTION);
System.out.println("SUBTRACTION: " + t.image);
          break;
          }
        case MULTIPLY:{
          t = jj_consume_token(MULTIPLY);
System.out.println("MULTIPLY: " + t.image);
          break;
          }
        case DIVISION:{
          t = jj_consume_token(DIVISION);
System.out.println("DIVISION: " + t.image);
          break;
          }
        case INTEGER_DIVISION:{
          t = jj_consume_token(INTEGER_DIVISION);
System.out.println("INTEGER_DIVISION: " + t.image);
          break;
          }
        case DIVISION_REMAINDER:{
          t = jj_consume_token(DIVISION_REMAINDER);
System.out.println("DIVISION_REMAINDER: " + t.image);
          break;
          }
        case POTENCE:{
          t = jj_consume_token(POTENCE);
System.out.println("POTENCE: " + t.image);
          break;
          }
        case SEMI_COLUMN:{
          t = jj_consume_token(SEMI_COLUMN);
System.out.println("SEMI_COLUMN: " + t.image);
          break;
          }
        case DOT:{
          t = jj_consume_token(DOT);
System.out.println("DOT: " + t.image);
          break;
          }
        case OPEN_KEYS:{
          t = jj_consume_token(OPEN_KEYS);
System.out.println("OPEN_KEYS: " + t.image);
          break;
          }
        case CLOSE_KEYS:{
          t = jj_consume_token(CLOSE_KEYS);
System.out.println("CLOSE_KEYS: " + t.image);
          break;
          }
        case OPEN_BOX:{
          t = jj_consume_token(OPEN_BOX);
System.out.println("OPEN_BOX: " + t.image);
          break;
          }
        case CLOSE_BOX:{
          t = jj_consume_token(CLOSE_BOX);
System.out.println("CLOSE_BOX: " + t.image);
          break;
          }
        case OPEN_BRACKETS:{
          t = jj_consume_token(OPEN_BRACKETS);
System.out.println("OPEN_BRACKETS: " + t.image);
          break;
          }
        case CLOSE_BRACKETS:{
          t = jj_consume_token(CLOSE_BRACKETS);
System.out.println("CLOSE_BRACKETS: " + t.image);
          break;
          }
        case IDENTIFIER:{
          t = jj_consume_token(IDENTIFIER);
System.out.println("IDENTIFIER: " + t.image);
          break;
          }
        case INTEGER_NUMERICAL_CONSTANT:{
          t = jj_consume_token(INTEGER_NUMERICAL_CONSTANT);
System.out.println("INTEGER_NUMERICAL_CONSTANT: " + t.image);
          break;
          }
        case REAL_NUMERICAL_CONSTANT:{
          t = jj_consume_token(REAL_NUMERICAL_CONSTANT);
System.out.println("REAL_NUMERICAL_CONSTANT: " + t.image);
          break;
          }
        case LITERAL_CONSTANT:{
          t = jj_consume_token(LITERAL_CONSTANT);
System.out.println("LITERAL_CONSTANT: " + t.image);
          break;
          }
        case ERROR_LITERAL_CONSTANT:{
          t = jj_consume_token(ERROR_LITERAL_CONSTANT);
concatError(t, "Literal constant quote not ended"); validLexical = false;
          break;
          }
        case ASCII:{
          t = jj_consume_token(ASCII);
concatError(t); validLexical = false;
          break;
          }
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      } catch (ParseException e) {

      }
    } finally {
      trace_return("lexicalAnalyzer");
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
      jj_la1_0 = new int[] {0xfffff000,0xfffff000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0xffffffff,0xffffffff,};
   }
   private static void jj_la1_init_2() {
      jj_la1_2 = new int[] {0x3c,0x3c,};
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
      trace_token(token, "");
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
      trace_token(token, " (in getNextToken)");
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
    boolean[] la1tokens = new boolean[79];
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
    for (int i = 0; i < 79; i++) {
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

  private int trace_indent = 0;
  private boolean trace_enabled = true;

/** Enable tracing. */
  final public void enable_tracing() {
    trace_enabled = true;
  }

/** Disable tracing. */
  final public void disable_tracing() {
    trace_enabled = false;
  }

  private void trace_call(String s) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Call:   " + s);
    }
    trace_indent = trace_indent + 2;
  }

  private void trace_return(String s) {
    trace_indent = trace_indent - 2;
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Return: " + s);
    }
  }

  private void trace_token(Token t, String where) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Consumed token: <" + tokenImage[t.kind]);
      if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
        System.out.print(": \"" + t.image + "\"");
      }
      System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
    }
  }

  private void trace_scan(Token t1, int t2) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Visited token: <" + tokenImage[t1.kind]);
      if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
        System.out.print(": \"" + t1.image + "\"");
      }
      System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
    }
  }

}
