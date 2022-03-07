package me.maxih.vscript;

import java.util.logging.Level;

public class Logger {
    Level level = Level.INFO;
    String name;

    public Logger(String name) {
        this.name = name;
    }

    public void error(String msg) {
        if (Level.SEVERE.intValue() >= this.level.intValue()) {
            System.out.println("[ERROR] " + msg);
        }
    }

    public void warn(String msg) {
        if (Level.WARNING.intValue() >= this.level.intValue()) {
            System.out.println("[WARNING] " + msg);
        }
    }

    public void info(String msg) {
        if (Level.INFO.intValue() >= this.level.intValue()) {
            System.out.println("[INFO] " + msg);
        }
    }

    public void debug(String msg) {
        if (Level.FINE.intValue() >= this.level.intValue()) {
            System.out.println("[DEBUG] " + msg);
        }
    }
}
