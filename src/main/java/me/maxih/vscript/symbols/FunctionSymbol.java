package me.maxih.vscript.symbols;

import me.maxih.vscript.gen.VScriptParser;

import java.util.LinkedHashMap;
import java.util.Map;

public class FunctionSymbol extends Symbol implements Scope {
    Map<String, Symbol> parameters = new LinkedHashMap<>();  // Linked for order
    Scope enclosingScope;
    VScriptParser.BlockContext blockTree;

    public FunctionSymbol(String name, Scope enclosingScope, VScriptParser.BlockContext blockTree) {
        super(name);
        this.enclosingScope = enclosingScope;
        this.blockTree = blockTree;
    }

    @Override
    public Scope getEnclosingScope() {
        return this.enclosingScope;
    }

    @Override
    public void define(Symbol symbol) {
        this.parameters.put(symbol.getName(), symbol);
        symbol.scope = this;
    }

    @Override
    public Symbol resolve(String name) {
        if (parameters.containsKey(name)) return parameters.get(name);
        if (this.getEnclosingScope() != null) return this.getEnclosingScope().resolve(name);
        return null;
    }

    public Map<String, Symbol> getParameters() {
        return this.parameters;
    }

    public int getParameterCount() {
        return this.parameters.size();
    }

    public VScriptParser.BlockContext getBlockTree() {
        return this.blockTree;
    }

    @Override
    public String toString() {
        String parameterList = this.parameters.values().toString();
        return super.toString() + "(" + parameterList.substring(1, parameterList.length() - 1) + ")";
    }
}
