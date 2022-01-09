package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.LongArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Instant;

import static org.bukkit.BanList.Type.NAME;

public class Tempban {
    public static void register(){
        new CommandAPICommand("tempban")
                .withPermission("usefulcommands.tempban")
                .withArguments(new PlayerArgument("player"))
                .withArguments(new LongArgument("time"))
                .withArguments(new StringArgument("timeFormat").replaceSuggestions(suggestions ->
                        new String[] {"s", "m", "h", "d"}
                ))
                .withArguments(new GreedyStringArgument("reason"))
                .executes((sender, args) -> {
                    long mili = switch (args[2].toString().toLowerCase()) {
                        case "minutes", "min", "m" -> (long) args[1] * 60000;
                        case "hours", "h" -> (long) args[1] * 3600000;
                        case "days", "d" -> (long) args[1] * 86400000;
                        default -> (long) args[1];
                    };
                    java.util.Date time = new java.util.Date(Instant.now().getEpochSecond()*1000+mili);
                    Bukkit.getBanList(NAME).addBan(String.valueOf(((Player)args[0]).getUniqueId()), String.valueOf(args[3]), time, String.valueOf(sender));
                    ((Player)args[0]).kickPlayer(String.valueOf(args[3]));
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " has been banned for " + args[1] + args[2] + " for " + args[3]);
                }).register();
    }
}
