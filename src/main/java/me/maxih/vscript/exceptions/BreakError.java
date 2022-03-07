package me.maxih.vscript.exceptions;

import org.antlr.v4.runtime.Token;

public class BreakError extends RuntimeException {
    public Token breakToken;

    public BreakError() {
        super("");
    }

}
