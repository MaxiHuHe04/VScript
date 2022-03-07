package me.maxih.vscript.exceptions;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

public class SyntaxError extends RuntimeException {
    Object offendingSymbol;
    int line;
    int charPositionInLine;
    RecognitionException recognitionException;

    public SyntaxError(String msg, RecognitionException cause) {
        super(msg, cause);
        Token offendingToken = cause.getOffendingToken();
        this.offendingSymbol = offendingToken;
        this.line = offendingToken.getLine();
        this.charPositionInLine = offendingToken.getCharPositionInLine();
        this.recognitionException = cause;
    }

    public SyntaxError(String msg, Object offendingSymbol, int line, int charPositionInLine) {
        super(msg);
        this.offendingSymbol = offendingSymbol;
        this.line = line;
        this.charPositionInLine = charPositionInLine;
    }

    public int getLine() {
        return line;
    }

    public int getCharPositionInLine() {
        return charPositionInLine;
    }

    /**
     * @return Underlying recognition exception or null
     */
    public RecognitionException getRecognitionException() {
        return recognitionException;
    }
}
