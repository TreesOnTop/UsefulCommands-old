package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.util.Util;
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
                    Player p = (Player) args[0];
                    p.setAllowFlight(!p.getAllowFlight());
                    p.sendMessage(Util.outputPrefix() + "Your flight has been set to "
                            + p.getAllowFlight());
                    sender.sendMessage(Util.outputPrefix() + p.getName()
                            + "'s flight has been set to " + p.getAllowFlight());
                }).register();
        new CommandAPICommand("fly")
                .withPermission(CommandPermission.fromString("usefulcommands.fly"))
                .executesPlayer((sender, args) -> {
                    sender.setAllowFlight(!sender.getAllowFlight());
                    sender.sendMessage(Util.outputPrefix()
                            + "Your flight has been set to " + sender.getAllowFlight());
                }).register();
    }
}
