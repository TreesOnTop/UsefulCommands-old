package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Objects;

import static org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH;

public class Heal {
    public static void register(){
        new CommandAPICommand("heal")
                .withPermission("usefulcommands.heal")
                .executesPlayer((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    sender.setHealth(Objects.requireNonNull(sender.getAttribute(GENERIC_MAX_HEALTH)).getValue());
                    sender.sendMessage(config.getString("OutputPrefix") + "You have been healed");
                }).register();
        new CommandAPICommand("heal")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.heal.others"))
                .executes((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    ((Player)args[0]).setHealth(Objects.requireNonNull(((Player)args[0]).getAttribute(GENERIC_MAX_HEALTH)).getValue());
                    ((Player)args[0]).sendMessage(config.getString("OutputPrefix") + "You have been healed");
                    sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + " has been healed");
                }).register();
    }
}
