package me.maxih.vscript.exceptions;

import org.antlr.v4.runtime.Token;

public class ProgramError extends RuntimeException {
    Token token;

    public ProgramError(String msg, Token token) {
        super(msg + " at '" + token.getText() + "' in line " + token.getLine());
        this.token = token;
    }

    public Token getToken() {
        return this.token;
    }

    public int getLine() {
        return this.token.getLine();
    }

    public int getColumn() {
        return this.token.getCharPositionInLine();
    }
}
