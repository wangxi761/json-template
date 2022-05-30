// Generated from D:/Data/Workspace/java/antlrDemo/src/main/resources\JSON.g4 by ANTLR 4.10.1
package org.wx.json.template.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JSONParser}.
 */
public interface JSONListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JSONParser#json}.
	 * @param ctx the parse tree
	 */
	void enterJson(JSONParser.JsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#json}.
	 * @param ctx the parse tree
	 */
	void exitJson(JSONParser.JsonContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#obj}.
	 * @param ctx the parse tree
	 */
	void enterObj(JSONParser.ObjContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#obj}.
	 * @param ctx the parse tree
	 */
	void exitObj(JSONParser.ObjContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(JSONParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(JSONParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#arr}.
	 * @param ctx the parse tree
	 */
	void enterArr(JSONParser.ArrContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#arr}.
	 * @param ctx the parse tree
	 */
	void exitArr(JSONParser.ArrContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(JSONParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(JSONParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#str}.
	 * @param ctx the parse tree
	 */
	void enterStr(JSONParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#str}.
	 * @param ctx the parse tree
	 */
	void exitStr(JSONParser.StrContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#num}.
	 * @param ctx the parse tree
	 */
	void enterNum(JSONParser.NumContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#num}.
	 * @param ctx the parse tree
	 */
	void exitNum(JSONParser.NumContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(JSONParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(JSONParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(JSONParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(JSONParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#null}.
	 * @param ctx the parse tree
	 */
	void enterNull(JSONParser.NullContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#null}.
	 * @param ctx the parse tree
	 */
	void exitNull(JSONParser.NullContext ctx);
}