package com.github.zac694.usefulcommands.functions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("ConstantConditions")
public class Utils {
    private static Class<?> packetClass;
    private static Class<?> minecraftServerClass;

    public static void startCache() {
        try {
            packetClass = getNMSClass("Packet");
            minecraftServerClass = getNMSClass("MinecraftServer");
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public static String getNMSVersion() {
        String version = Bukkit.getServer().getClass().getPackage().getName();
        version = version.substring(version.lastIndexOf('.') + 1);
        return version;
    }

    public static Object getHandle(Player p) {
        try {
            return p.getClass().getMethod("getHandle").invoke(p);
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
    /**
     * @return true if the packet sent, false otherwise.
     */
    public static boolean sendPacket(Player p, String packetClassName, Class<?>[] targetConstructor, Object...constructorData) {
        try {
            Class<?> thePacketClass = getNMSClass(packetClassName);
            Object nmsHandle = getHandle(p);
            Field playerConnectionField = nmsHandle.getClass().getField("playerConnection");
            Object playerConnection = playerConnectionField.get(nmsHandle);
            Method sendPacketMethod = playerConnection.getClass().getMethod("sendPacket", packetClass);
            Constructor<?> thePacketConstruct = thePacketClass.getConstructor(targetConstructor);
            Object thePacket = thePacketConstruct.newInstance(constructorData);
            sendPacketMethod.invoke(playerConnection, thePacket);
        } catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Field getEntityPlayerField(Player p, String name) {
        try {
            return getHandle(p).getClass().getField(name);
        } catch (IllegalArgumentException | SecurityException | NoSuchFieldException e) { return null; }
    }

    public static int getPing(Player p) {
        try {
            return getEntityPlayerField(p, "ping").getInt(getHandle(p));
        } catch (IllegalArgumentException | IllegalAccessException e) { return -1; }
    }

    public static Class<?> getNMSClass(String className) {
        try {
            return Class.forName("net.minecraft.server."+getNMSVersion()+"." + className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static double[] getTPS() {
        try {
            Method m = minecraftServerClass.getMethod("getServer");
            Object server = m.invoke(null);
            Field recentTps = server.getClass().getField("recentTps");
            return (double[]) recentTps.get(server);
        } catch (NoSuchMethodException | SecurityException | NoSuchFieldException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
