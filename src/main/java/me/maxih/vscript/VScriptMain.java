package me.maxih.vscript;

import me.maxih.vscript.exceptions.AnalyzeError;
import me.maxih.vscript.exceptions.ProgramError;
import me.maxih.vscript.exceptions.SyntaxError;
import me.maxih.vscript.gen.VScriptLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.io.Console;
import java.util.Scanner;
import java.util.Stack;

public class VScriptMain {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                CharStream stream = CharStreams.fromFileName(args[0]);
                new Interpreter().interpret(stream);
            } catch (SyntaxError | AnalyzeError e) {
                System.err.println();
                System.exit(1);
            } catch (ProgramError e) {
                System.err.println(e.getMessage() + " col " + (e.getColumn() + 1));
                System.exit(1);
            } catch (Throwable e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(1);
            }
        } else {
            repl();
        }
    }

    public static boolean bracketsRemaining(String code) {
        VScriptLexer lexer = new VScriptLexer(CharStreams.fromString(code));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        Stack<String> brackets = new Stack<>();
        for (Token token : tokens.getTokens()) {
            String t = token.getText();
            switch (t) {
                case "(" -> brackets.push(")");
                case "[" -> brackets.push("]");
                case "{" -> brackets.push("}");
                case ")", "]", "}" -> {
                    if (brackets.isEmpty() || !brackets.pop().equals(t)) return false;  // Mismatched closing brackets
                }
            }
        }
        return !brackets.isEmpty();
    }

    public static void repl() {
        Console console = System.console();
        Scanner scanner = new Scanner(System.in);
        Interpreter interpreter = new Interpreter(true);

        while (true) {
            StringBuilder builder = new StringBuilder();

            System.out.print("> ");
            do {
                String line = console != null ? console.readLine() : scanner.nextLine();
                if (builder.isEmpty() && line.strip().equals("/exit")) return;
                builder.append(line).append("\n");
            } while (bracketsRemaining(builder.toString()));

            CharStream codeStream = CharStreams.fromString(builder.toString());

            try {
                interpreter.interpret(codeStream);
            } catch (SyntaxError | AnalyzeError e) {
                System.out.println();
            } catch (ProgramError e) {
                System.out.println(e.getMessage() + " col " + (e.getColumn() + 1));
            } catch (Throwable e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }
}
