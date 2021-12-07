package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.TimeArgument;
import org.bukkit.entity.Player;

public class Tempmute {
    public static void register(){
        new CommandAPICommand("tempmute")
                .withPermission(CommandPermission.fromString("usefulcommands.tempmute"))
                .withArguments(new PlayerArgument("target"))
                .withArguments(new TimeArgument("time"))
                .executes((sender, args) -> {
                    long untime = (((long)args[1])*50+System.currentTimeMillis());
                    if(ConfigHandler.getData().contains("tmuted." + ((Player)args[0]).getUniqueId())){
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " is already muted");
                        return;
                    }
                    ConfigHandler.getData().set("tmuted." + ((Player)args[0]).getUniqueId(), untime);
                    ConfigHandler.save();
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " has been muted for " + args[1]);
                    ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are now muted for " + args[1]);
                }).register();
    }
}
