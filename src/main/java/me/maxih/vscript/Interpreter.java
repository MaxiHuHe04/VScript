package me.maxih.vscript;

import me.maxih.vscript.exceptions.*;
import me.maxih.vscript.gen.VScriptBaseVisitor;
import me.maxih.vscript.gen.VScriptLexer;
import me.maxih.vscript.gen.VScriptParser;
import me.maxih.vscript.memory.BlockSpace;
import me.maxih.vscript.memory.FunctionSpace;
import me.maxih.vscript.memory.MemorySpace;
import me.maxih.vscript.symbols.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Interpreter extends VScriptBaseVisitor<Object> {
    GlobalScope globalScope;
    MemorySpace globals;
    MemorySpace currentSpace;
    ParseTreeProperty<Scope> nodeScopes;

    ReturnValue returnValue = new ReturnValue();
    BreakError breakError = new BreakError();

    boolean replMode;

    public Interpreter(GlobalScope globalScope, MemorySpace globals, ParseTreeProperty<Scope> nodeScopes, boolean replMode) {
        this.globalScope = globalScope;
        this.globals = globals;
        this.currentSpace = this.globals;
        this.replMode = replMode;
        this.nodeScopes = nodeScopes;
    }

    public Interpreter(boolean replMode) throws AnalyzeError, SyntaxError {
        this(new GlobalScope(), new MemorySpace("globals"), new ParseTreeProperty<>(), replMode);
    }

    public Interpreter() {
        this(false);
    }

    public void interpret(CharStream stream) throws SyntaxError, AnalyzeError, ProgramError {
        VScriptLexer lexer = new VScriptLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        VScriptParser parser = new VScriptParser(tokens);

        // Log all syntax errors and throw the first one afterwards
        SyntaxErrorMemory syntaxErrorMemory = new SyntaxErrorMemory();
        parser.addErrorListener(syntaxErrorMemory);
        VScriptParser.ProgramContext program = parser.program();
        syntaxErrorMemory.throwFirst();

        // Log all analyze errors and throw the first one afterwards
        ParseTreeWalker walker = new ParseTreeWalker();
        StaticAnalyzer analyzer = new StaticAnalyzer(this.globalScope, this.nodeScopes);
        walker.walk(analyzer, program);
        if (!analyzer.isValid()) {
            throw analyzer.getErrors().get(0);
        }

        this.visit(program);
    }

    public MemorySpace getSpaceWithSymbol(String id) {
        if (this.currentSpace.has(id)) return this.currentSpace;
        if (this.globals.has(id)) return this.globals;
        return null;
    }

    public boolean valuesEqual(Object left, Object right) {
        if (left == right) return true;
        if (left == null || right == null) return false;

        if (left instanceof BigDecimal a && right instanceof BigDecimal b) return a.compareTo(b) == 0;
        else if (left instanceof String a && right instanceof String b) return a.equals(b);
        else if (left instanceof Boolean a && right instanceof Boolean b) return a.equals(b);
        else if (left instanceof List<?> a && right instanceof List<?> b) return a.equals(b);
        else return false;
    }

    @Override
    public Object visitProgram(VScriptParser.ProgramContext ctx) {
        try {
            return super.visitProgram(ctx);
        } catch (ReturnValue e) {
            throw new ProgramError("Return outside of function", e.returnToken);
        } catch (BreakError e) {
            throw new ProgramError("Break outside of loop", e.breakToken);
        }
    }

    @Override
    public Object visitFuncDef(VScriptParser.FuncDefContext ctx) {
        return null;
    }

    @Override
    public Object visitStat(VScriptParser.StatContext ctx) {
        if (ctx.expr() != null) {
            Object result = this.visitExpr(ctx.expr());
            if (this.replMode && result != null) System.out.println(result);
            return result;
        }
        return super.visitStat(ctx);
    }

    @Override
    public Object visitIfStat(VScriptParser.IfStatContext ctx) {
        Object condition = this.visit(ctx.expr());
        if (!(condition instanceof Boolean bool)) throw new TypeError("Incompatible type for condition", ctx.expr().start);
        if (bool) {
            this.visitBlock(ctx.thenBlock);
        } else if (ctx.elseIf != null) {
            this.visitIfStat(ctx.elseIf);
        } else if (ctx.elseBlock != null) {
            this.visitBlock(ctx.elseBlock);
        }
        return null;
    }

    @Override
    public Object visitWhileStat(VScriptParser.WhileStatContext ctx) {
        while (true) {
            Object condition = this.visit(ctx.expr());
            if (!(condition instanceof Boolean bool))
                throw new TypeError("Incompatible type for condition", ctx.expr().start);
            if (!bool) break;

            try {
                this.visitBlock(ctx.block());
            } catch (BreakError breakError) {
                break;
            }
        }
        return null;
    }

    @Override
    public Object visitReturnStat(VScriptParser.ReturnStatContext ctx) {
        this.returnValue.value = this.visit(ctx.expr());
        this.returnValue.returnToken = ctx.start;
        throw returnValue;
    }

    @Override
    public Object visitBreakStat(VScriptParser.BreakStatContext ctx) {
        breakError.breakToken = ctx.start;
        throw breakError;
    }

    @Override
    public Object visitCall(VScriptParser.CallContext ctx) {
        Scope functionEnclosingScope = this.nodeScopes.get(ctx);
        if (functionEnclosingScope == null) throw new ProgramError("Function not found", ctx.start);
        FunctionSymbol func = (FunctionSymbol) functionEnclosingScope.resolve(ctx.ID().getText());
        if (ctx.args.size() != func.getParameterCount()) {
            throw new ProgramError("Invalid number of arguments", ctx.start);
        }
        FunctionSpace functionSpace = new FunctionSpace(func);
        int i = 0;
        for (Symbol argSym : func.getParameters().values()) {
            VariableSymbol arg = (VariableSymbol) argSym;
            Object argValue = visit(ctx.args.get(i));
            functionSpace.put(arg.getName(), argValue);
            i++;
        }

        MemorySpace callingSpace = this.currentSpace;
        this.currentSpace = functionSpace;
        try {
            if (func instanceof BuiltinFunctionSymbol builtin) return builtin.execute(functionSpace);
            else visit(func.getBlockTree());
        } catch (ReturnValue returnValue) {
            return returnValue.value;
        } catch (StackOverflowError e) {
            throw new ProgramError("Stack overflow", ctx.start);
        } finally {
            this.currentSpace = callingSpace;
        }
        return null;
    }

    @Override
    public Object visitBlock(VScriptParser.BlockContext ctx) {
        MemorySpace parentSpace = this.currentSpace;
        this.currentSpace = new BlockSpace(parentSpace);
        Object result;
        try {
            result = super.visitBlock(ctx);
        } finally {
            this.currentSpace = parentSpace;
        }
        return result;
    }

    @Override
    public Object visitVarDecl(VScriptParser.VarDeclContext ctx) {
        String varName = ctx.ID().getText();
        if (this.currentSpace.has(varName)) {
            throw new ProgramError("Duplicate variable declaration", ctx.ID().getSymbol());
        } else {
            this.currentSpace.put(varName, visit(ctx.expr()));
        }
        return null;
    }

    @Override
    public Object visitAssignment(VScriptParser.AssignmentContext ctx) {
        String varName = ctx.ID().getText();
        MemorySpace space = getSpaceWithSymbol(varName);
        if (space == null) {
            throw new ProgramError("Unknown variable", ctx.ID().getSymbol());
        }
        space.put(varName, visit(ctx.expr()));
        return null;
    }

    @Override
    public Object visitArrayAssignment(VScriptParser.ArrayAssignmentContext ctx) {
        String varName = ctx.ID().getText();
        MemorySpace space = getSpaceWithSymbol(varName);
        if (space == null) throw new ProgramError("Unknown variable", ctx.ID().getSymbol());
        if (!(space.get(varName) instanceof List<?> array)) throw new ProgramError("Not an array", ctx.ID().getSymbol());

        Token indexStartToken = ctx.index.start;
        try {
            if (!(visit(ctx.index) instanceof BigDecimal index)) throw new ProgramError("Invalid index", indexStartToken);
            int i = index.intValueExact();
            //noinspection unchecked
            ((List<Object>) array).set(i, visit(ctx.value));
        } catch (ArithmeticException e) {
            throw new TypeError("Invalid index", indexStartToken);
        } catch (IndexOutOfBoundsException e) {
            throw new TypeError(e.getMessage(), indexStartToken);
        }
        return null;
    }

    @Override
    public Object visitExpr(VScriptParser.ExprContext ctx) {
        if (ctx.atom() != null) return this.visit(ctx.atom());

        if (ctx.unaryOp != null) {
            Object operand = this.visitExpr(ctx.expr(0));

            switch (ctx.unaryOp.getText()) {
                case "!" -> {
                    if (operand instanceof Boolean bool) return !bool;
                    else throw new TypeError("Incompatible operand", ctx.unaryOp);
                }
                case "-" -> {
                    if (operand instanceof BigDecimal number) return number.negate();
                    else throw new TypeError("Incompatible operand", ctx.unaryOp);
                }
                default -> throw new UnsupportedOperationException();
            }
        }

        String op = ctx.op.getText();
        Object left = this.visitExpr(ctx.expr(0));
        Object right = this.visitExpr(ctx.expr(1));

        if (Set.of("*", "/", "%", "-", ">", "<", ">=", "<=").contains(op)) {
            if (!(left instanceof BigDecimal a)) throw new TypeError("Incompatible left operand", ctx.op);
            if (!(right instanceof BigDecimal b)) throw new TypeError("Incompatible right operand", ctx.op);
            switch (op) {
                case "*":
                    return a.multiply(b);
                case "/":
                    if (b.equals(BigDecimal.ZERO)) throw new ProgramError("Division by zero", ctx.op);
                    return a.divide(b, MathContext.DECIMAL64);
                case "%":
                    if (b.equals(BigDecimal.ZERO)) throw new ProgramError("Division by zero", ctx.op);
                    return a.divideAndRemainder(b, MathContext.UNLIMITED)[1];
                case "-":
                    return a.subtract(b);
                case ">":
                    return a.compareTo(b) > 0;
                case "<":
                    return a.compareTo(b) < 0;
                case ">=":
                    return a.compareTo(b) >= 0;
                case "<=":
                    return a.compareTo(b) <= 0;
                default:
                    throw new UnsupportedOperationException();
            }
        } else {
            switch (op) {
                case "+":
                    if (left instanceof BigDecimal a) {
                        if (right instanceof BigDecimal b) return a.add(b);
                        else if (right instanceof String b) return a + b;
                        else throw new TypeError("Incompatible right operand", ctx.op);
                    } else if (left instanceof String a) {
                        if (right instanceof BigDecimal b) return a + b;
                        else if (right instanceof String b) return a + b;
                        else throw new TypeError("Incompatible right operand", ctx.op);
                    } else if (left instanceof List<?> a) {
                        if (right instanceof List<?> b) {
                            List<Object> clone = new ArrayList<>(a);
                            clone.addAll(b);
                            return clone;
                        } else throw new TypeError("Incompatible right operand", ctx.op);
                    } else {
                        throw new TypeError("Incompatible left operand", ctx.op);
                    }
                case "==":
                    return valuesEqual(left, right);
                case "!=":
                    return !valuesEqual(left, right);
                case "&&":
                    if (!(left instanceof Boolean a)) throw new TypeError("Incompatible left operand", ctx.op);
                    if (!(right instanceof Boolean b)) throw new TypeError("Incompatible right operand", ctx.op);
                    return a && b;
                case "||":
                    if (!(left instanceof Boolean a)) throw new TypeError("Incompatible left operand", ctx.op);
                    if (!(right instanceof Boolean b)) throw new TypeError("Incompatible right operand", ctx.op);
                    return a || b;
                case "[":
                    Token indexStartToken = ctx.expr(1).start;
                    if (!(left instanceof List<?> array)) throw new TypeError("Not an array", ctx.start);
                    if (!(right instanceof BigDecimal index)) throw new TypeError("Invalid index", indexStartToken);
                    try {
                        int i = index.intValueExact();
                        return array.get(i);
                    } catch (ArithmeticException e) {
                        throw new TypeError("Invalid index", indexStartToken);
                    } catch (IndexOutOfBoundsException e) {
                        throw new TypeError(e.getMessage(), indexStartToken);
                    }
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public Object visitNumberAtom(VScriptParser.NumberAtomContext ctx) {
        return new BigDecimal(ctx.getText());
    }

    @Override
    public Object visitBooleanAtom(VScriptParser.BooleanAtomContext ctx) {
        return ctx.TRUE() != null;
    }

    @Override
    public Object visitStringAtom(VScriptParser.StringAtomContext ctx) {
        String text = ctx.getText();
        return text
                .substring(1, text.length() - 1)
                .replace("\\\\", "\\")
                .replace("\\\"", "\"")
                .replace("\\t", "\t")
                .replace("\\n", "\n")
                .replace("\\r", "\r");
    }

    @Override
    public Object visitNullAtom(VScriptParser.NullAtomContext ctx) {
        return null;
    }

    @Override
    public Object visitIdAtom(VScriptParser.IdAtomContext ctx) {
        String text = ctx.getText();

        if (this.currentSpace.has(text)) return this.currentSpace.get(text);
        if (this.globals.has(text)) return this.globals.get(text);
        throw new ProgramError("Unknown variable", ctx.start);
    }

    @Override
    public Object visitParensExpr(VScriptParser.ParensExprContext ctx) {
        return this.visitExpr(ctx.expr());
    }

    @Override
    public Object visitArray(VScriptParser.ArrayContext ctx) {
        List<Object> list = new ArrayList<>(ctx.expr().size());
        list.addAll(ctx.expr().stream().map(this::visit).toList());
        return list;
    }
}
