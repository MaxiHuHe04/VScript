package me.maxih.vscript.symbols;

public class LocalScope extends BaseScope {
    Scope parentScope;

    public LocalScope(Scope parent) {
        this.parentScope = parent;
    }

    @Override
    public Scope getEnclosingScope() {
        return this.parentScope;
    }

}
