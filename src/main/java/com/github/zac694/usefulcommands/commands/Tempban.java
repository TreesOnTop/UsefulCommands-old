package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Instant;

import static org.bukkit.BanList.Type.NAME;

public class Tempban {
    public static void register(){
        new CommandAPICommand("tempban")
                .withPermission("usefulcommands.tempban")
                .withArguments(new PlayerArgument("player"))
                .withArguments(new DoubleArgument("time"))
                .withArguments(new StringArgument("timeFormat"))
                .withArguments(new GreedyStringArgument("reason"))
                .executes((sender, args) -> {
                    double milli;
                    switch (args[2].toString().toLowerCase()) {
                        case "minutes":
                        case "min":
                        case "m":
                            milli = (double)args[1] * 60000;
                            break;
                        case "hours":
                        case "h":
                            milli = (double)args[1] * 3600000;
                            break;
                        case "days":
                        case "d":
                            milli = (double)args[1] * 86400000;
                            break;
                        default:
                            milli = (double)args[1];
                            break;
                    }
                    java.util.Date time = new java.util.Date(Instant.now().getEpochSecond()*1000+Math.round(milli));
                    Bukkit.getBanList(NAME).addBan(String.valueOf(((Player)args[0]).getUniqueId()), String.valueOf(args[3]), time, String.valueOf(sender));
                    ((Player)args[0]).kickPlayer(String.valueOf(args[3]));
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " has been banned for " + args[1] + args[2] + " for " + args[3]);
                }).register();
    }
}
