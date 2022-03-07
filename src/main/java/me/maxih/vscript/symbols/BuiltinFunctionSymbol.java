package me.maxih.vscript.symbols;

import me.maxih.vscript.builtins.BuiltinFunction;
import me.maxih.vscript.interpreter.FunctionSpace;

import java.util.function.Function;

public class BuiltinFunctionSymbol extends FunctionSymbol {
    Function<FunctionSpace, Object> callback;

    public BuiltinFunctionSymbol(BuiltinFunction builtin, Scope enclosingScope) {
        super(builtin.name(), enclosingScope, null);
        this.callback = builtin.func();
        for (String parameter : builtin.parameterNames()) {
            this.parameters.put(parameter, new VariableSymbol(parameter));
        }
    }

    public Object execute(FunctionSpace space) {
        return this.callback.apply(space);
    }
}
