package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Objects;

public class Spawn{
    public static void register(){
        new CommandAPICommand("spawn")
                .withPermission("usefulcommands.spawn")
                .executesPlayer((sender, args) -> {
                    File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration data = ConfigHandler.getConfig(dataFile);
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    if(data.contains("spawn")){
                        sender.teleport(Objects.requireNonNull(data.getLocation("spawn")));
                        sender.sendMessage(config.getString("OutputPrefix") + "Sent you to spawn");
                    }
                    else{
                        sender.sendMessage(config.getString("OutputPrefix") + "Spawn isn't set");
                    }
                }).register();
        new CommandAPICommand("spawn")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.spawn.others"))
                .executesPlayer((sender, args) -> {
                    File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration data = ConfigHandler.getConfig(dataFile);
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    if(data.contains("spawn")){
                        Player p = (Player)args[0];
                        p.teleport(Objects.requireNonNull(data.getLocation("spawn")));
                        p.sendMessage(config.getString("OutputPrefix") + "You were sent to spawn");
                    }
                    else{
                        sender.sendMessage(config.getString("OutputPrefix") + "Spawn isn't set");
                    }
                }).register();
    }
}
