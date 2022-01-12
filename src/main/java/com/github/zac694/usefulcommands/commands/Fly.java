package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Fly {
    public static void register(){
        new CommandAPICommand("fly")
                .withPermission("usefulcommands.fly")
                .executesPlayer((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    sender.setAllowFlight(!sender.getAllowFlight());
                    sender.sendMessage(config.getString("OutputPrefix") + "Your flight has been set to " + sender.getAllowFlight());
                }).register();
        new CommandAPICommand("fly")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.fly.others"))
                .executes((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    ((Player)args[0]).setAllowFlight(!((Player) args[0]).getAllowFlight());
                    ((Player)args[0]).sendMessage(config.getString("OutputPrefix") + "Your flight has been set to " + ((Player)args[0]).getAllowFlight());
                    sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + "'s flight has been set to " + ((Player)args[0]).getAllowFlight());
                }).register();
    }
}
