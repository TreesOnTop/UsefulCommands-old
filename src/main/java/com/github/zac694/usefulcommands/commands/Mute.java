package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Mute {
    public static void register(){
        new CommandAPICommand("mute")
                .withPermission("usefulcommands.mute")
                .withArguments(new PlayerArgument("player"))
                .executes((sender, args) -> {
                    File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration data = ConfigHandler.getConfig(dataFile);
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    if(data.getBoolean("muted." + ((Player)args[0]).getUniqueId())){
                        sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + " is already muted");
                    }else{
                        data.set("muted." + ((Player) args[0]).getUniqueId(), true);
                        ConfigHandler.save(dataFile, data);
                        sender.sendMessage(config.getString("OutputPrefix") + ((Player) args[0]).getName() + " is now muted");
                        ((Player) args[0]).sendMessage(config.getString("OutputPrefix") + "You are now muted");
                    }
                }).register();
    }
}
