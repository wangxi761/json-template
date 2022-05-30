// Generated from D:/Data/Workspace/java/antlrDemo/src/main/resources\JSON.g4 by ANTLR 4.10.1
package com.huya.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JSONParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JSONVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JSONParser#json}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJson(JSONParser.JsonContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSONParser#obj}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObj(JSONParser.ObjContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSONParser#pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(JSONParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSONParser#arr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArr(JSONParser.ArrContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSONParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(JSONParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSONParser#str}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr(JSONParser.StrContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSONParser#num}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum(JSONParser.NumContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSONParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(JSONParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSONParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(JSONParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSONParser#null}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(JSONParser.NullContext ctx);
}