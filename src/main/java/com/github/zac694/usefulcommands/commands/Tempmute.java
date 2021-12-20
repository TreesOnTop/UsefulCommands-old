package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.DoubleArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.entity.Player;

import java.time.Instant;

public class Tempmute {
    public static void register(){
        new CommandAPICommand("tempmute")
                .withPermission("usefulcommands.tempmute")
                .withArguments(new PlayerArgument("target"))
                .withArguments(new DoubleArgument("time"))
                .withArguments(new StringArgument("timeFormat"))
                .executes((sender, args) -> {
                    double sec;
                    switch(args[2].toString().toLowerCase()){
                        case "minutes":
                        case "min":
                        case "m":
                            sec = (double)args[1]*60;
                            break;
                        case "hours":
                        case "h":
                            sec = (double)args[1]*3600;
                            break;
                        case "days":
                        case "d":
                            sec = (double)args[1]*86400;
                            break;
                        default:
                            sec = (double)args[1];
                    }
                    if(ConfigHandler.getData().contains("tmuted." + ((Player)args[0]).getUniqueId())){
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " is already muted");
                        return;
                    }
                    ConfigHandler.getData().set("tmuted." + ((Player)args[0]).getUniqueId(), sec + Instant.now().getEpochSecond());
                    ConfigHandler.save();
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " has been muted for " + args[1] + args[2]);
                    ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are now muted for " + args[1] + args[2]);
                }).register();
    }
}
