package com.github.zac694.usefulcommands;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"})
public class ConfigHandler {
    private static FileConfiguration config;
    private static FileConfiguration data;

    public static void setup(){
        File configFile = new File(Bukkit.getServer().getPluginManager().getPlugin("UsefulCommands").getDataFolder(), "config.yml");
        if(!configFile.exists()){
            Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("UsefulCommands")).saveResource("config.yml", true);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        if(!config.contains("OutputPrefix") || !config.contains("Broadcast")){
            Bukkit.getConsoleSender().sendMessage("§cConfig file corrupted regenerating");
            configFile.delete();
            Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("UsefulCommands")).saveResource("config.yml", true);
            config = YamlConfiguration.loadConfiguration(configFile);
        }
        File dataFile = new File(Bukkit.getServer().getPluginManager().getPlugin("UsefulCommands").getDataFolder(), "data.yml");
        if(!dataFile.exists()){
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§cUnable to create config");
            }
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
    }
    public static FileConfiguration getConfig(){
        return config;
    }
    public static FileConfiguration getData(){
        return data;
    }
    public static File getConfigFile(){
        return new File(Bukkit.getServer().getPluginManager().getPlugin("UsefulCommands").getDataFolder(), "config.yml");
    }
    public static File getDataFile(){
        return new File(Bukkit.getServer().getPluginManager().getPlugin("UsefulCommands").getDataFolder(), "data.yml");
    }
    public static void save(){
        try{
            File dataFile = new File(Bukkit.getServer().getPluginManager().getPlugin("UsefulCommands").getDataFolder(), "data.yml");
            data.save(dataFile);
        }catch(IOException e){
            Bukkit.getConsoleSender().sendMessage("§cUnable to save config");
        }
    }
    public static void reload(){
        File dataFile = new File(Bukkit.getServer().getPluginManager().getPlugin("UsefulCommands").getDataFolder(), "data.yml");
        File configFile = new File(Bukkit.getServer().getPluginManager().getPlugin("UsefulCommands").getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        data = YamlConfiguration.loadConfiguration(dataFile);
    }
}
