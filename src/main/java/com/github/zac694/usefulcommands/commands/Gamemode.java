package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.util.Util;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

import static org.bukkit.GameMode.*;

public class Gamemode {
    public static void register(){
        new CommandAPICommand("gmc")
                .withArguments(new PlayerArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.gamemode.creative.others"))
                .executes((sender, args) -> {
                    ((Player)args[0]).setGameMode(CREATIVE);
                    ((Player)args[0]).sendMessage(Util.outputPrefix() + "Your gamemode has been set to creative");
                    sender.sendMessage(Util.outputPrefix() + ((Player)args[0]).getName() + "'s gamemode has been set to creative");
                }).register();
        new CommandAPICommand("gmc")
                .withPermission(CommandPermission.fromString("usefulcommands.gamemode.creative"))
                .executesPlayer((sender, args) -> {
                    sender.setGameMode(CREATIVE);
                    sender.sendMessage(Util.outputPrefix() + "Your gamemode has been set to creative");
                }).register();
        new CommandAPICommand("gms")
                .withArguments(new PlayerArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.gamemode.surival.others"))
                .executes((sender, args) -> {
                    ((Player)args[0]).setGameMode(SURVIVAL);
                    ((Player)args[0]).sendMessage(Util.outputPrefix() + "Your gamemode has been set to survival");
                    sender.sendMessage(Util.outputPrefix() + ((Player)args[0]).getName() + "'s gamemode has been set to survival");
                }).register();
        new CommandAPICommand("gms")
                .withPermission(CommandPermission.fromString("usefulcommands.gamemode.survival"))
                .executesPlayer((sender, args) -> {
                    sender.setGameMode(SURVIVAL);
                    sender.sendMessage(Util.outputPrefix() + "Your gamemode has been set to survival");
                }).register();
        new CommandAPICommand("gmsp")
                .withArguments(new PlayerArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.gamemode.spectator.others"))
                .executes((sender, args) -> {
                    ((Player)args[0]).setGameMode(SPECTATOR);
                    ((Player)args[0]).sendMessage(Util.outputPrefix() + "Your gamemode has been set to spectator");
                    sender.sendMessage(Util.outputPrefix() + ((Player)args[0]).getName() + "'s gamemode has been set to spectator");
                }).register();
        new CommandAPICommand("gmsp")
                .withPermission(CommandPermission.fromString("usefulcommands.gamemode.spectator"))
                .executesPlayer((sender, args) -> {
                    sender.setGameMode(SPECTATOR);
                    sender.sendMessage(Util.outputPrefix() + "Your gamemode has been set to spectator");
                }).register();
        new CommandAPICommand("gma")
                .withArguments(new PlayerArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.gamemode.adventure.others"))
                .executes((sender, args) -> {
                    ((Player)args[0]).setGameMode(ADVENTURE);
                    ((Player)args[0]).sendMessage(Util.outputPrefix() + "Your gamemode has been set to adventure");
                    sender.sendMessage(Util.outputPrefix() + ((Player)args[0]).getName() + "'s gamemode has been set to adventure");
                }).register();
        new CommandAPICommand("gma")
                .withPermission(CommandPermission.fromString("usefulcommands.gamemode.adventure"))
                .executesPlayer((sender, args) -> {
                    sender.setGameMode(ADVENTURE);
                    sender.sendMessage(Util.outputPrefix() + "Your gamemode has been set to adventure");
                }).register();
    }
}
