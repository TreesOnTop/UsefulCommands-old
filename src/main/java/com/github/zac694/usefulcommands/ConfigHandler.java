package com.github.zac694.usefulcommands;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ConfigHandler {
    public static File fileInit(String name, Plugin plugin) {
        File file = new File(plugin.getDataFolder(), name);
        if(!file.exists()) {
            file.getParentFile().mkdir();
            plugin.saveResource(name, true);
        }
        return file;
    }

    public static void save(File file, YamlConfiguration config) {
        try {
            config.save(file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getConfig(File file) {
        YamlConfiguration config = new YamlConfiguration();
        try{
            config.load(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        return config;
    }
}
