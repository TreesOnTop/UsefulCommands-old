package com.github.zac694.usefulcommands;

import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Utils {
    public static Object getHandle(Player p) {
        try {
            return p.getClass().getMethod("getHandle").invoke(p);
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
    public static Field getEntityPlayerField(Player p, String name) {
        try {
            return Objects.requireNonNull(getHandle(p)).getClass().getField(name);
        } catch (IllegalArgumentException | SecurityException | NoSuchFieldException e) { return null; }
    }
    public static int getPing(Player p) {
        try {
            return Objects.requireNonNull(getEntityPlayerField(p, "ping")).getInt(getHandle(p));
        } catch (IllegalArgumentException | IllegalAccessException e) { return -1; }
    }
}
