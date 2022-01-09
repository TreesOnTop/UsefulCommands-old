package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.functions.GetLocation;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

public class Spawn{
    public static void register(){
        new CommandAPICommand("spawn")
                .withPermission("usefulcommands.spawn")
                .executesPlayer((sender, args) -> {
                    if(ConfigHandler.getData().contains("spawn")){
                        sender.sendMessage("test");
                        sender.teleport(GetLocation.getLoc("spawn"));
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Sent you to spawn");
                    }
                    else{
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Spawn isn't set");
                    }
                }).register();
        new CommandAPICommand("spawn")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.spawn.others"))
                .executes((sender, args) -> {
                    if(ConfigHandler.getData().contains("spawn")){
                        Player p = (Player)args[0];
                        p.teleport(GetLocation.getLoc("spawn"));
                        p.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You were sent to spawn");
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Sent" + p.getName() + "to spawn");
                    }
                    else{
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Spawn isn't set");
                    }
                }).register();
    }
}
