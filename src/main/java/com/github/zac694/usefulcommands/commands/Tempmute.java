package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.entity.Player;

import java.time.Instant;

public class Tempmute {
    public static void register(){
        new CommandAPICommand("tempmute")
                .withPermission(CommandPermission.fromString("usefulcommands.tempmute"))
                .withArguments(new PlayerArgument("target"))
                .withArguments(new StringArgument("time"))
                .executes((sender, args) -> {
                    String timeFormat = args[1].toString().substring(args[1].toString().length() - 1);
                    double time = Double.parseDouble(args[1].toString().substring(0, args[1].toString().length()-1));
                    long sec;
                    switch(timeFormat.toLowerCase()){
                        case "m":
                            sec = (long)Math.floor(time*60);
                            break;
                        case "h":
                            sec = (long)Math.floor(time*3600);
                            break;
                        case "d":
                            sec = (long)Math.floor(time*86400);
                            break;
                        default:
                            sec = (long)Math.floor(time);
                    }
                    if(ConfigHandler.getData().contains("tmuted." + ((Player)args[0]).getUniqueId())){
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " is already muted");
                        return;
                    }
                    ConfigHandler.getData().set("tmuted." + ((Player)args[0]).getUniqueId(), sec + Instant.now().getEpochSecond());
                    ConfigHandler.save();
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " has been muted for " + args[1]);
                    ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are now muted for " + args[1]);
                }).register();
    }
}
