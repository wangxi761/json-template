// Generated from D:/Data/Workspace/java/antlrDemo/src/main/resources\JSON.g4 by ANTLR 4.10.1
package org.wx.json.template.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JSONLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, FALSE=7, TRUE=8, NULL=9, 
		VAR=10, STRING=11, NUMBER=12, WS=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "FALSE", "TRUE", "NULL", 
			"VAR", "STRING", "ESC", "UNICODE", "HEX", "SAFECODEPOINT", "NUMBER", 
			"INT", "EXP", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "':'", "'['", "']'", "'false'", "'true'", 
			"'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "FALSE", "TRUE", "NULL", "VAR", 
			"STRING", "NUMBER", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public JSONLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JSON.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\r\u008e\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0005\tI\b\t\n\t\f\tL\t\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0005\nS\b\n\n\n\f\nV\t\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b]\b\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0003\u000fj\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0004\u000f"+
		"o\b\u000f\u000b\u000f\f\u000fp\u0003\u000fs\b\u000f\u0001\u000f\u0003"+
		"\u000fv\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010{\b\u0010"+
		"\n\u0010\f\u0010~\t\u0010\u0003\u0010\u0080\b\u0010\u0001\u0011\u0001"+
		"\u0011\u0003\u0011\u0084\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0004"+
		"\u0012\u0089\b\u0012\u000b\u0012\f\u0012\u008a\u0001\u0012\u0001\u0012"+
		"\u0000\u0000\u0013\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\u0000\u0019"+
		"\u0000\u001b\u0000\u001d\u0000\u001f\f!\u0000#\u0000%\r\u0001\u0000\b"+
		"\b\u0000\"\"//\\\\bbffnnrrtt\u0003\u000009AFaf\u0003\u0000\u0000\u001f"+
		"\"\"\\\\\u0001\u000009\u0001\u000019\u0002\u0000EEee\u0002\u0000++--\u0003"+
		"\u0000\t\n\r\r  \u0094\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0001\'\u0001\u0000\u0000"+
		"\u0000\u0003)\u0001\u0000\u0000\u0000\u0005+\u0001\u0000\u0000\u0000\u0007"+
		"-\u0001\u0000\u0000\u0000\t/\u0001\u0000\u0000\u0000\u000b1\u0001\u0000"+
		"\u0000\u0000\r3\u0001\u0000\u0000\u0000\u000f9\u0001\u0000\u0000\u0000"+
		"\u0011>\u0001\u0000\u0000\u0000\u0013C\u0001\u0000\u0000\u0000\u0015O"+
		"\u0001\u0000\u0000\u0000\u0017Y\u0001\u0000\u0000\u0000\u0019^\u0001\u0000"+
		"\u0000\u0000\u001bd\u0001\u0000\u0000\u0000\u001df\u0001\u0000\u0000\u0000"+
		"\u001fi\u0001\u0000\u0000\u0000!\u007f\u0001\u0000\u0000\u0000#\u0081"+
		"\u0001\u0000\u0000\u0000%\u0088\u0001\u0000\u0000\u0000\'(\u0005{\u0000"+
		"\u0000(\u0002\u0001\u0000\u0000\u0000)*\u0005,\u0000\u0000*\u0004\u0001"+
		"\u0000\u0000\u0000+,\u0005}\u0000\u0000,\u0006\u0001\u0000\u0000\u0000"+
		"-.\u0005:\u0000\u0000.\b\u0001\u0000\u0000\u0000/0\u0005[\u0000\u0000"+
		"0\n\u0001\u0000\u0000\u000012\u0005]\u0000\u00002\f\u0001\u0000\u0000"+
		"\u000034\u0005f\u0000\u000045\u0005a\u0000\u000056\u0005l\u0000\u0000"+
		"67\u0005s\u0000\u000078\u0005e\u0000\u00008\u000e\u0001\u0000\u0000\u0000"+
		"9:\u0005t\u0000\u0000:;\u0005r\u0000\u0000;<\u0005u\u0000\u0000<=\u0005"+
		"e\u0000\u0000=\u0010\u0001\u0000\u0000\u0000>?\u0005n\u0000\u0000?@\u0005"+
		"u\u0000\u0000@A\u0005l\u0000\u0000AB\u0005l\u0000\u0000B\u0012\u0001\u0000"+
		"\u0000\u0000CD\u0005$\u0000\u0000DE\u0005{\u0000\u0000EJ\u0001\u0000\u0000"+
		"\u0000FI\u0003\u0017\u000b\u0000GI\u0003\u001d\u000e\u0000HF\u0001\u0000"+
		"\u0000\u0000HG\u0001\u0000\u0000\u0000IL\u0001\u0000\u0000\u0000JH\u0001"+
		"\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000KM\u0001\u0000\u0000\u0000"+
		"LJ\u0001\u0000\u0000\u0000MN\u0005}\u0000\u0000N\u0014\u0001\u0000\u0000"+
		"\u0000OT\u0005\"\u0000\u0000PS\u0003\u0017\u000b\u0000QS\u0003\u001d\u000e"+
		"\u0000RP\u0001\u0000\u0000\u0000RQ\u0001\u0000\u0000\u0000SV\u0001\u0000"+
		"\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000UW\u0001"+
		"\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000WX\u0005\"\u0000\u0000X\u0016"+
		"\u0001\u0000\u0000\u0000Y\\\u0005\\\u0000\u0000Z]\u0007\u0000\u0000\u0000"+
		"[]\u0003\u0019\f\u0000\\Z\u0001\u0000\u0000\u0000\\[\u0001\u0000\u0000"+
		"\u0000]\u0018\u0001\u0000\u0000\u0000^_\u0005u\u0000\u0000_`\u0003\u001b"+
		"\r\u0000`a\u0003\u001b\r\u0000ab\u0003\u001b\r\u0000bc\u0003\u001b\r\u0000"+
		"c\u001a\u0001\u0000\u0000\u0000de\u0007\u0001\u0000\u0000e\u001c\u0001"+
		"\u0000\u0000\u0000fg\b\u0002\u0000\u0000g\u001e\u0001\u0000\u0000\u0000"+
		"hj\u0005-\u0000\u0000ih\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000"+
		"jk\u0001\u0000\u0000\u0000kr\u0003!\u0010\u0000ln\u0005.\u0000\u0000m"+
		"o\u0007\u0003\u0000\u0000nm\u0001\u0000\u0000\u0000op\u0001\u0000\u0000"+
		"\u0000pn\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qs\u0001\u0000"+
		"\u0000\u0000rl\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000su\u0001"+
		"\u0000\u0000\u0000tv\u0003#\u0011\u0000ut\u0001\u0000\u0000\u0000uv\u0001"+
		"\u0000\u0000\u0000v \u0001\u0000\u0000\u0000w\u0080\u00050\u0000\u0000"+
		"x|\u0007\u0004\u0000\u0000y{\u0007\u0003\u0000\u0000zy\u0001\u0000\u0000"+
		"\u0000{~\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000"+
		"\u0000\u0000}\u0080\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000"+
		"\u007fw\u0001\u0000\u0000\u0000\u007fx\u0001\u0000\u0000\u0000\u0080\""+
		"\u0001\u0000\u0000\u0000\u0081\u0083\u0007\u0005\u0000\u0000\u0082\u0084"+
		"\u0007\u0006\u0000\u0000\u0083\u0082\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0086"+
		"\u0003!\u0010\u0000\u0086$\u0001\u0000\u0000\u0000\u0087\u0089\u0007\u0007"+
		"\u0000\u0000\u0088\u0087\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000"+
		"\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000"+
		"\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008d\u0006\u0012"+
		"\u0000\u0000\u008d&\u0001\u0000\u0000\u0000\u000e\u0000HJRT\\ipru|\u007f"+
		"\u0083\u008a\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}