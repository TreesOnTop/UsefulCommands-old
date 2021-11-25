package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

public class Fly {
    public static void register(){
        new CommandAPICommand("fly")
                .withArguments(new PlayerArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.fly.others"))
                .executes((sender, args) -> {
                    ((Player)args[0]).setAllowFlight(!((Player) args[0]).getAllowFlight());
                    ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Your flight has been set to " + ((Player)args[0]).getAllowFlight());
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + "'s flight has been set to " + ((Player)args[0]).getAllowFlight());
                }).register();
        new CommandAPICommand("fly")
                .withPermission(CommandPermission.fromString("usefulcommands.fly"))
                .executesPlayer((sender, args) -> {
                    sender.setAllowFlight(!sender.getAllowFlight());
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Your flight has been set to " + sender.getAllowFlight());
                }).register();
    }
}
