package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class God {
    public static void register(){
        new CommandAPICommand("god")
                .withPermission("usefulcommands.god")
                .executesPlayer((sender, args) -> {
                    File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration data = ConfigHandler.getConfig(dataFile);
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    if(data.getBoolean("god." + sender.getUniqueId())) {
                        data.set("god." + sender.getUniqueId(), null);
                        sender.sendMessage(config.getString("OutputPrefix") + "You are no longer in god mode");
                    }else{
                        data.set("god." + sender.getUniqueId(), true);
                        sender.sendMessage(config.getString("OutputPrefix") + "You are now in god mode");
                    }
                    ConfigHandler.save(dataFile, data);
                }).register();
        new CommandAPICommand("god")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.god.others"))
                .executes((sender, args) -> {
                    File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration data = ConfigHandler.getConfig(dataFile);
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    if(data.getBoolean("god." + ((Player)args[0]).getUniqueId())) {
                        data.set("god." + ((Player)args[0]).getUniqueId(), null);
                        ((Player)args[0]).sendMessage(config.getString("OutputPrefix") + "You are no longer in god mode");
                        sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + " is no longer god mode");
                    }else{
                        data.set("god." + ((Player)args[0]).getUniqueId(), true);
                        sender.sendMessage(config.getString("OutputPrefix") + ((Player) args[0]).getName() + " is now in god mode");
                        ((Player) args[0]).sendMessage(config.getString("OutputPrefix") + "You are now in god mode");
                    }
                    ConfigHandler.save(dataFile, data);
                }).register();
    }
}