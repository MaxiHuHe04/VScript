package me.maxih.vscript.exceptions;

import org.antlr.v4.runtime.Token;

public class TypeError extends ProgramError {
    public TypeError(String msg, Token token) {
        super(msg, token);
    }
}
