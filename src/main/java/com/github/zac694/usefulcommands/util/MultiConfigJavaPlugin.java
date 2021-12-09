package com.github.zac694.usefulcommands.util;

import com.google.common.base.Charsets;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class MultiConfigJavaPlugin extends JavaPlugin {
    /* ex:
        reloadConfig("config.yml");
        saveDefaultConfig("config.yml");
        getConfig("config.yml").options().copyDefaults(true);
        saveConfig("config.yml");
    */

    private final Map<String, File> configFiles = new HashMap<>();
    private final Map<String, FileConfiguration> newConfigs = new HashMap<>();

    public void reloadAllConfig() {
        configFiles.keySet().forEach(this::reloadConfig);
    }

    @Override
    public void reloadConfig() {
        reloadConfig("config.yml");
    }

    public void reloadConfig(String fileName) {
        if (configFiles.get(fileName) == null || !configFiles.get(fileName).exists()) {
            configFiles.put(fileName, new File(this.getDataFolder(), fileName));
        }

        newConfigs.put(fileName, YamlConfiguration.loadConfiguration(configFiles.get(fileName)));

        final InputStream configStream = getResource(fileName);
        if (configStream == null) {
            return;
        }

        newConfigs.get(fileName).setDefaults(
                YamlConfiguration.loadConfiguration(
                        new InputStreamReader(configStream, Charsets.UTF_8)));
    }

    @Override
    public void saveConfig() {
        saveConfig("config.yml");
    }

    public void saveConfig(String fileName) {
        try {
            getConfig(fileName).save(configFiles.get(fileName));
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config to " + fileName, ex);
        }
    }

    @Override
    public FileConfiguration getConfig() {
        return getConfig("config.yml");
    }

    public FileConfiguration getConfig(String fileName) {
        if (newConfigs.get(fileName) == null) {
            reloadConfig(fileName);
        }
        return newConfigs.get(fileName);
    }

    @Override
    public void saveDefaultConfig() {
        saveDefaultConfig("config.yml");
    }

    public void saveDefaultConfig(String fileName) {
        if (!configFiles.get(fileName).exists()) {
            saveResource(fileName, false);
        }
    }
}
