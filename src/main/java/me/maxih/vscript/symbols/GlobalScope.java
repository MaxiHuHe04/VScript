package me.maxih.vscript.symbols;

import me.maxih.vscript.builtins.BuiltinFunction;
import me.maxih.vscript.builtins.Builtins;

public class GlobalScope extends BaseScope {

    public GlobalScope() {
        for (BuiltinFunction builtin : Builtins.builtinFunctions) {
            this.define(new BuiltinFunctionSymbol(builtin, this));
        }
    }

    @Override
    public Scope getEnclosingScope() {
        return null;
    }

}
