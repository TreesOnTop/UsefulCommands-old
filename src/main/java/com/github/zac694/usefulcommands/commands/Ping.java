package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Ping {
    public static void register(){
        new CommandAPICommand("ping")
                .withAliases("p")
                .executesPlayer((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    sender.sendMessage(config.getString("OutputPrefix") + "Your ping is " + sender.getPing());
                }).register();
        new CommandAPICommand("ping")
                .withArguments(new PlayerArgument("player"))
                .withAliases("p")
                .executes((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + "'s ping is " + ((Player)args[0]).getPing());
                }).register();
    }
}
