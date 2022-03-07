package me.maxih.vscript.symbols;

public interface Scope {
    Scope getEnclosingScope();
    void define(Symbol symbol);
    Symbol resolve(String name);
}
