package me.maxih.vscript.memory;

public class BlockSpace extends MemorySpace {
    MemorySpace parent;

    public BlockSpace(MemorySpace parent) {
        super("");
        this.parent = parent;
    }

    @Override
    public boolean has(String id) {
        return this.members.containsKey(id) || this.parent.has(id);
    }

    @Override
    public Object get(String id) {
        if (this.members.containsKey(id)) return this.members.get(id);
        else if (this.parent.has(id)) return this.parent.get(id);
        else return null;
    }

    @Override
    public void put(String id, Object value) {
        if (this.members.containsKey(id)) this.members.put(id, value);
        else if (this.parent.has(id)) this.parent.put(id, value);
        else this.members.put(id, value);
    }
}
