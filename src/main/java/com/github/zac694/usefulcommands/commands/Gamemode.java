package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

import static org.bukkit.GameMode.*;

public class Gamemode {
    public static void register(){
        new CommandAPICommand("gmc")
                .withPermission("usefulcommands.gamemode.creative")
                .executesPlayer((sender, args) -> {
                    sender.setGameMode(CREATIVE);
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Your gamemode has been set to creative");
                }).register();
        new CommandAPICommand("gmc")
                .withArguments(new PlayerArgument("target").withPermission("usefulcommands.gamemode.creative.others"))
                .executes((sender, args) -> {
                    ((Player)args[0]).setGameMode(CREATIVE);
                    ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Your gamemode has been set to creative");
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + "'s gamemode has been set to creative");
                }).register();
        new CommandAPICommand("gms")
                .withPermission("usefulcommands.gamemode.survival")
                .executesPlayer((sender, args) -> {
                    sender.setGameMode(SURVIVAL);
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Your gamemode has been set to survival");
                }).register();
        new CommandAPICommand("gms")
                .withArguments(new PlayerArgument("target").withPermission("usefulcommands.gamemode.surival.others"))
                .executes((sender, args) -> {
                    ((Player)args[0]).setGameMode(SURVIVAL);
                    ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Your gamemode has been set to survival");
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + "'s gamemode has been set to survival");
                }).register();
        new CommandAPICommand("gmsp")
                .withPermission("usefulcommands.gamemode.spectator")
                .executesPlayer((sender, args) -> {
                    sender.setGameMode(SPECTATOR);
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Your gamemode has been set to spectator");
                }).register();
        new CommandAPICommand("gmsp")
                .withArguments(new PlayerArgument("target").withPermission("usefulcommands.gamemode.spectator.others"))
                .executes((sender, args) -> {
                    ((Player)args[0]).setGameMode(SPECTATOR);
                    ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Your gamemode has been set to spectator");
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + "'s gamemode has been set to spectator");
                }).register();
        new CommandAPICommand("gma")
                .withPermission("usefulcommands.gamemode.adventure")
                .executesPlayer((sender, args) -> {
                    sender.setGameMode(ADVENTURE);
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Your gamemode has been set to adventure");
                }).register();
        new CommandAPICommand("gma")
                .withArguments(new PlayerArgument("target").withPermission("usefulcommands.gamemode.adventure.others"))
                .executes((sender, args) -> {
                    ((Player)args[0]).setGameMode(ADVENTURE);
                    ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Your gamemode has been set to adventure");
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + "'s gamemode has been set to adventure");
                }).register();
    }
}
