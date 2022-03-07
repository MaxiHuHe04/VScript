// Generated from VScript.g4 by ANTLR 4.9.2

package me.maxih.vscript.gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link VScriptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface VScriptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link VScriptParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(VScriptParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(VScriptParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(VScriptParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(VScriptParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(VScriptParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(VScriptParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#arrayAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAssignment(VScriptParser.ArrayAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#ifStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(VScriptParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#whileStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(VScriptParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#returnStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(VScriptParser.ReturnStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#breakStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStat(VScriptParser.BreakStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(VScriptParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberAtom(VScriptParser.NumberAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanAtom(VScriptParser.BooleanAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAtom(VScriptParser.StringAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullAtom(VScriptParser.NullAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdAtom(VScriptParser.IdAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallAtom(VScriptParser.CallAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAtom(VScriptParser.ArrayAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parensExpr}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParensExpr(VScriptParser.ParensExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(VScriptParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link VScriptParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(VScriptParser.ArrayContext ctx);
}