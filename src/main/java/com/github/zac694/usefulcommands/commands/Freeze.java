package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Freeze {
    public static void register(){
        new CommandAPICommand("freeze")
                .withPermission("usefulcommands.freeze")
                .withArguments(new PlayerArgument("player"))
                .executes((sender, args) -> {
                    File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration data = ConfigHandler.getConfig(dataFile);
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    if(data.getBoolean("frozen." + ((Player)args[0]).getUniqueId())){
                        data.set("frozen." + ((Player)args[0]).getUniqueId(), null);
                        sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + " is no longer frozen");
                        ((Player)args[0]).sendMessage(config.getString("OutputPrefix") + "You are no longer frozen");
                    }else{
                        data.set("frozen." + ((Player) args[0]).getUniqueId(), true);
                        sender.sendMessage(config.getString("OutputPrefix") + ((Player) args[0]).getName() + " is now frozen");
                        ((Player) args[0]).sendMessage(config.getString("OutputPrefix") + "You are now frozen");
                    }
                    ConfigHandler.save(dataFile, data);
                }).register();
    }
}
