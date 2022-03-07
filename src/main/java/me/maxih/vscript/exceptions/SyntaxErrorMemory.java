package me.maxih.vscript.exceptions;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public class SyntaxErrorMemory extends BaseErrorListener {
    List<RuntimeException> errors = new ArrayList<>();

    public List<RuntimeException> getErrors() {
        return errors;
    }

    /**
     * Throw the first exception if there is one
     */
    public void throwFirst() throws RuntimeException {
        if (!this.errors.isEmpty()) {
            throw this.errors.get(0);
        }
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        this.errors.add(e == null
                ? new SyntaxError(msg, offendingSymbol, line, charPositionInLine)
                : new SyntaxError(msg, e));
    }
}
