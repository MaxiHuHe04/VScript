// Generated from VScript.g4 by ANTLR 4.9.2

package me.maxih.vscript.gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link VScriptParser}.
 */
public interface VScriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link VScriptParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(VScriptParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(VScriptParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(VScriptParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(VScriptParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(VScriptParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(VScriptParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(VScriptParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(VScriptParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(VScriptParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(VScriptParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(VScriptParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(VScriptParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#arrayAssignment}.
	 * @param ctx the parse tree
	 */
	void enterArrayAssignment(VScriptParser.ArrayAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#arrayAssignment}.
	 * @param ctx the parse tree
	 */
	void exitArrayAssignment(VScriptParser.ArrayAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(VScriptParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(VScriptParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(VScriptParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(VScriptParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(VScriptParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(VScriptParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#breakStat}.
	 * @param ctx the parse tree
	 */
	void enterBreakStat(VScriptParser.BreakStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#breakStat}.
	 * @param ctx the parse tree
	 */
	void exitBreakStat(VScriptParser.BreakStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(VScriptParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(VScriptParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNumberAtom(VScriptParser.NumberAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNumberAtom(VScriptParser.NumberAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterBooleanAtom(VScriptParser.BooleanAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitBooleanAtom(VScriptParser.BooleanAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterStringAtom(VScriptParser.StringAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitStringAtom(VScriptParser.StringAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNullAtom(VScriptParser.NullAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNullAtom(VScriptParser.NullAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterIdAtom(VScriptParser.IdAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitIdAtom(VScriptParser.IdAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterCallAtom(VScriptParser.CallAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitCallAtom(VScriptParser.CallAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterArrayAtom(VScriptParser.ArrayAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayAtom}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitArrayAtom(VScriptParser.ArrayAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parensExpr}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterParensExpr(VScriptParser.ParensExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parensExpr}
	 * labeled alternative in {@link VScriptParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitParensExpr(VScriptParser.ParensExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(VScriptParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(VScriptParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link VScriptParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(VScriptParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link VScriptParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(VScriptParser.ArrayContext ctx);
}