package LexicalAnalysis;/* LexicalAnalysis.java */
/* Generated By:JavaCC: Do not edit this line. LexicalAnalysis.java */
import java.io.*;

public class LexicalAnalysis implements LexicalAnalysisConstants {

        private String result = "";

        public String getResult(){
            return result;
        }

        public void setResult(String result){
            this.result = result;
        }

        private void concatError(Token t){
            this.setResult(this.getResult() + "## Lexic error, invalid token ("+t.image+") located at [line:"+t.beginLine+"| column:"+t.beginColumn+"] ##\u005cn");
        }

        private void concatMessage(Token t, String category, int categoryNumber){
                 this.setResult("Token: " + t.image + " of category: (" + category + ":" + categoryNumber + ") " + "\u005cn");
        }

  final public void MainRule() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SPECIAL_SYMBOLS:
      case IDENTIFIER:
      case LITERAL_CONSTANT:
      case INTEGER_NUMERICAL_CONSTANT:
      case REAL_NUMERICAL_CONSTANT:
      case RESERVED_WORD:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      lexicalAnalyzer();
    }
  }

  final public void lexicalAnalyzer() throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case RESERVED_WORD:{
      jj_consume_token(RESERVED_WORD);
      this.concatMessage(token, "RESERVED_WORD", RESERVED_WORD);
      break;
      }
      case SPECIAL_SYMBOLS:{
        jj_consume_token(SPECIAL_SYMBOLS);
        this.concatMessage(token, "SPECIAL_SYMBOLS", SPECIAL_SYMBOLS);
        break;
      }
      case IDENTIFIER:{
        jj_consume_token(IDENTIFIER);
        this.concatMessage(token, "IDENTIFIER", IDENTIFIER);
        break;
      }
      case INTEGER_NUMERICAL_CONSTANT:{
        jj_consume_token(INTEGER_NUMERICAL_CONSTANT);
        this.concatMessage(token, "INTEGER_NUMERICAL_CONSTANT", INTEGER_NUMERICAL_CONSTANT);
        break;
      }
      case REAL_NUMERICAL_CONSTANT:{
        jj_consume_token(REAL_NUMERICAL_CONSTANT);
        this.concatMessage(token,"REAL_NUMERICAL_CONSTANT", REAL_NUMERICAL_CONSTANT);
        break;
      }
      case LITERAL_CONSTANT:{
        jj_consume_token(LITERAL_CONSTANT);
        this.concatMessage(token, "LITERAL_CONSTANT", LITERAL_CONSTANT);
        break;
      }
      case ASCII:{
        this.concatMessage(token, "ASCII", ASCII);
        break;
      }
      default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      this.concatError(token);
      throw new ParseException();
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
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x3f0,0x3f0,};
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
    boolean[] la1tokens = new boolean[51];
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
        }
      }
    }
    for (int i = 0; i < 51; i++) {
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
