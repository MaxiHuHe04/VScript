package me.maxih.vscript.builtins;

import me.maxih.vscript.interpreter.FunctionSpace;

import java.util.function.Function;

public record BuiltinFunction(String name, Function<FunctionSpace, Object> func, String... parameterNames) {
}
