package me.maxih.vscript.builtins;

import me.maxih.vscript.memory.FunctionSpace;

import java.util.function.Function;

public record BuiltinFunction(String name, Function<FunctionSpace, Object> func, String... parameterNames) {
}
