package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.util.Util;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;

import org.bukkit.entity.Player;

public class Ping {
    public static void register(){
        new CommandAPICommand("ping")
                .withArguments(new PlayerArgument("target"))
                .withAliases("p")
                .executes((sender, args) -> {
                    sender.sendMessage(Util.outputPrefix() + ((Player)args[0]).getName()
                            + "'s ping is " + ((Player)args[0]).getPing());
                }).register();

        new CommandAPICommand("ping")
                .withAliases("p")
                .executesPlayer((sender, args) -> {
                    sender.sendMessage(Util.outputPrefix() + "Your ping is " + sender.getPing());
                }).register();
    }
}
