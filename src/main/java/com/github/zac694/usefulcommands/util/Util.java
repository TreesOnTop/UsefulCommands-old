package com.github.zac694.usefulcommands.util;

import com.github.zac694.usefulcommands.UsefulCommands;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Util {
    public static void setMain(UsefulCommands mainClass) { Util.mainClass = mainClass; }
    private static UsefulCommands mainClass;
    public static UsefulCommands main() { return mainClass; }

    private static final Set<String> configList = new HashSet<>();
    /**
     * all purpose reload, create file from resourse if there isn't one, get the new user input,
     * or save the edited value
     * @param name of the file
     */
    public static void reloadConfig(String name) {
        main().reloadConfig(name);
        main().saveDefaultConfig(name);
        main().getConfig(name).options().copyDefaults(true);
        main().saveConfig(name);
    }
    public static void registerConfig(String name) { configList.add(name); }
    /**
     * only work if you registerConfig()
     */
    public static void reloadAllConfig() {
        configList.forEach(Util::reloadConfig);
    }
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void deleteConfig(String name) {
        new File(main().getDataFolder(), name).delete();
    }
    public static void deleteAllConfig() {
        configList.forEach(Util::deleteConfig);
    }

    public static String outputPrefix() { return main().getConfig().getString("OutputPrefix"); }
}
