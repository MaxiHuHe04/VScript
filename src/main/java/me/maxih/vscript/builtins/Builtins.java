package me.maxih.vscript.builtins;

import java.util.Arrays;

public class Builtins {

    public static BuiltinFunction[] builtinFunctions = {
            new BuiltinFunction("print", space -> {
                Object object = space.get("value");
                if (object instanceof Object[] array) System.out.print(Arrays.toString(array));
                else System.out.print(object);
                return null;
            }, "value"),
            new BuiltinFunction("println", space -> {
                Object object = space.get("value");
                if (object instanceof Object[] array) System.out.println(Arrays.toString(array));
                else System.out.println(object);
                return null;
            }, "value")
    };

}
