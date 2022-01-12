package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Feed {
    public static void register(){
        new CommandAPICommand("feed")
                .withPermission("usefulcommands.feed")
                .executesPlayer((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    sender.setFoodLevel(20);
                    sender.sendMessage(config.getString("OutputPrefix") + "You have been fed");
                }).register();
        new CommandAPICommand("feed")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.feed.others"))
                .executes((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    ((Player)args[0]).setFoodLevel(20);
                    ((Player)args[0]).sendMessage(config.getString("OutputPrefix") + "You have been fed");
                    sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + " has been fed");
                }).register();

    }
}
