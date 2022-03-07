package me.maxih.vscript.interpreter;

import me.maxih.vscript.symbols.FunctionSymbol;

public class FunctionSpace extends MemorySpace {

    public FunctionSpace(FunctionSymbol func) {
        super(func.getName());
    }

}
