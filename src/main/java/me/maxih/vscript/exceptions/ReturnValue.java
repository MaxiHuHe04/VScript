package me.maxih.vscript.exceptions;

import org.antlr.v4.runtime.Token;

public class ReturnValue extends RuntimeException {
    public Object value;
    public Token returnToken;

    public ReturnValue() {
        super("");
    }
}
