package me.maxih.vscript.interpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class MemorySpace {
    String name;
    Map<String, Object> members = new HashMap<>();

    public MemorySpace(String name) {
        this.name = name;
    }

    public boolean has(String id) {
        return this.members.containsKey(id);
    }

    public Object get(String id) {
        return this.members.get(id);
    }

    public void put(String id, Object value) {
        this.members.put(id, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MemorySpace.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("members=" + members)
                .toString();
    }
}
