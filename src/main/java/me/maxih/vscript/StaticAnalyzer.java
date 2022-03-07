package me.maxih.vscript;

import me.maxih.vscript.exceptions.AnalyzeError;
import me.maxih.vscript.gen.VScriptBaseListener;
import me.maxih.vscript.gen.VScriptParser;
import me.maxih.vscript.symbols.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.List;

public class StaticAnalyzer extends VScriptBaseListener {
    Logger logger = new Logger(getClass().getName());
    GlobalScope globalScope;
    Scope currentScope = null;
    List<AnalyzeError> errors = new ArrayList<>();
    ParseTreeProperty<Scope> nodeScopes;

    public StaticAnalyzer(GlobalScope globalScope, ParseTreeProperty<Scope> nodeScopes) {
        this.globalScope = globalScope;
        this.nodeScopes = nodeScopes;
    }

    public StaticAnalyzer() {
        this(new GlobalScope(), new ParseTreeProperty<>());
    }

    public boolean isValid() {
        return this.errors.size() == 0;
    }

    public List<AnalyzeError> getErrors() {
        return this.errors;
    }

    private void error(String msg, Token token) {
        AnalyzeError error = new AnalyzeError(msg, token);
        logger.error(error.getMessage());
        errors.add(error);
    }

    @Override
    public void enterProgram(VScriptParser.ProgramContext ctx) {
        this.currentScope = globalScope;
    }

    @Override
    public void exitProgram(VScriptParser.ProgramContext ctx) {
        this.currentScope = null;
    }

    @Override
    public void enterFuncDef(VScriptParser.FuncDefContext ctx) {
        logger.debug("Line " + ctx.start.getLine() + ": define function '" + ctx.name.getText() + "'");
        if (currentScope.resolve(ctx.name.getText()) != null)
            logger.debug("Overriding function '" + ctx.name.getText() + "'");
        this.nodeScopes.put(ctx, this.currentScope);
        FunctionSymbol func = new FunctionSymbol(ctx.name.getText(), this.currentScope, ctx.block());
        for (Token param : ctx.params) {
            func.define(new VariableSymbol(param.getText()));
        }
        this.currentScope.define(func);
        this.currentScope = func;
    }

    @Override
    public void exitFuncDef(VScriptParser.FuncDefContext ctx) {
        logger.debug("Exiting definition of function " + this.currentScope);
        this.currentScope = this.currentScope.getEnclosingScope();
    }

    @Override
    public void enterCall(VScriptParser.CallContext ctx) {
        Symbol sym = this.currentScope.resolve(ctx.ID().getText());
        if (sym instanceof FunctionSymbol funcSym) {
            this.nodeScopes.put(ctx, this.currentScope);
            if (funcSym.getParameterCount() != ctx.args.size()) {
                error("Invalid number of arguments", ctx.start);
            }
            logger.debug("Function call: '" + funcSym.getName() + "'");
        } else {
            error("Function not found", ctx.ID().getSymbol());
        }
    }

    @Override
    public void enterBlock(VScriptParser.BlockContext ctx) {
        logger.debug("Entering block");
        this.currentScope = new LocalScope(this.currentScope);
        this.nodeScopes.put(ctx, this.currentScope);
    }

    @Override
    public void exitBlock(VScriptParser.BlockContext ctx) {
        logger.debug("Exiting block. Defined variables: " + this.currentScope);
        this.currentScope = this.currentScope.getEnclosingScope();
    }

    @Override
    public void enterVarDecl(VScriptParser.VarDeclContext ctx) {
        String name = ctx.ID().getText();
        Symbol s = this.currentScope.resolve(name);
        if (s != null) {
            error("Duplicate variable declaration", ctx.ID().getSymbol());
        } else {
            this.currentScope.define(new VariableSymbol(name));
            this.nodeScopes.put(ctx, this.currentScope);
        }
    }

    @Override
    public void enterAssignment(VScriptParser.AssignmentContext ctx) {
        Symbol s = this.currentScope.resolve(ctx.ID().getText());
        if (s == null) {
            error("Unknown variable", ctx.ID().getSymbol());
        } else if (!(s instanceof VariableSymbol)) {
            error("Not a variable", ctx.ID().getSymbol());
        } else {
            this.nodeScopes.put(ctx, this.currentScope);
        }
    }

    @Override
    public void enterIdAtom(VScriptParser.IdAtomContext ctx) {
        Symbol s = this.currentScope.resolve(ctx.getText());
        if (s == null) {
            error("Unknown variable", ctx.ID().getSymbol());
        } else if (!(s instanceof VariableSymbol)) {
            error("Not a variable", ctx.ID().getSymbol());
        } else {
            this.nodeScopes.put(ctx, this.currentScope);
        }
    }

}
