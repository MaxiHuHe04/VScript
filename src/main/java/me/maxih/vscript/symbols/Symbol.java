package me.maxih.vscript.symbols;

public class Symbol {
    String name;
    Scope scope;

    public Symbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.getName();
    }
}
