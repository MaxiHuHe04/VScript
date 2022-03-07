package me.maxih.vscript.symbols;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseScope implements Scope {
    Map<String, Symbol> symbols = new LinkedHashMap<>();

    @Override
    public void define(Symbol symbol) {
        symbols.put(symbol.getName(), symbol);
        symbol.scope = this;
    }

    @Override
    public Symbol resolve(String name) {
        if (symbols.containsKey(name)) return symbols.get(name);
        if (this.getEnclosingScope() != null) return this.getEnclosingScope().resolve(name);
        return null;
    }

    @Override
    public String toString() {
        return this.symbols.keySet().toString();
    }
}
