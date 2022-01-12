package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

import static org.bukkit.GameMode.*;

public class Gamemode {
    public static void register(){
        new CommandAPICommand("gmc")
                .withPermission("usefulcommands.gamemode.creative")
                .executesPlayer((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    sender.setGameMode(CREATIVE);
                    sender.sendMessage(config.getString("OutputPrefix") + "Your gamemode has been set to creative");
                }).register();
        new CommandAPICommand("gmc")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.gamemode.creative.others"))
                .executes((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    ((Player)args[0]).setGameMode(CREATIVE);
                    ((Player)args[0]).sendMessage(config.getString("OutputPrefix") + "Your gamemode has been set to creative");
                    sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + "'s gamemode has been set to creative");
                }).register();
        new CommandAPICommand("gms")
                .withPermission("usefulcommands.gamemode.survival")
                .executesPlayer((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    sender.setGameMode(SURVIVAL);
                    sender.sendMessage(config.getString("OutputPrefix") + "Your gamemode has been set to survival");
                }).register();
        new CommandAPICommand("gms")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.gamemode.surival.others"))
                .executes((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    ((Player)args[0]).setGameMode(SURVIVAL);
                    ((Player)args[0]).sendMessage(config.getString("OutputPrefix") + "Your gamemode has been set to survival");
                    sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + "'s gamemode has been set to survival");
                }).register();
        new CommandAPICommand("gmsp")
                .withPermission("usefulcommands.gamemode.spectator")
                .executesPlayer((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    sender.setGameMode(SPECTATOR);
                    sender.sendMessage(config.getString("OutputPrefix") + "Your gamemode has been set to spectator");
                }).register();
        new CommandAPICommand("gmsp")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.gamemode.spectator.others"))
                .executes((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    ((Player)args[0]).setGameMode(SPECTATOR);
                    ((Player)args[0]).sendMessage(config.getString("OutputPrefix") + "Your gamemode has been set to spectator");
                    sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + "'s gamemode has been set to spectator");
                }).register();
        new CommandAPICommand("gma")
                .withPermission("usefulcommands.gamemode.adventure")
                .executesPlayer((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    sender.setGameMode(ADVENTURE);
                    sender.sendMessage(config.getString("OutputPrefix") + "Your gamemode has been set to adventure");
                }).register();
        new CommandAPICommand("gma")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.gamemode.adventure.others"))
                .executes((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    ((Player)args[0]).setGameMode(ADVENTURE);
                    ((Player)args[0]).sendMessage(config.getString("OutputPrefix") + "Your gamemode has been set to adventure");
                    sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + "'s gamemode has been set to adventure");
                }).register();
    }
}
