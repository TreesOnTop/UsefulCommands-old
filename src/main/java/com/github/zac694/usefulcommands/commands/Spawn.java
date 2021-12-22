package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Spawn{
    public static void register(){
        new CommandAPICommand("spawn")
                .withPermission("usefulcommands.spawn")
                .executesPlayer((sender, args) -> {
                    if(ConfigHandler.getData().contains("spawn")){
                        sender.teleport(Objects.requireNonNull(ConfigHandler.getData().getLocation("spawn")));
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Sent you to spawn");
                    }
                    else{
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Spawn isn't set");
                    }
                }).register();
        new CommandAPICommand("spawn")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.spawn.others"))
                .executesPlayer((sender, args) -> {
                    if(ConfigHandler.getData().contains("spawn")){
                        Player p = (Player)args[0];
                        p.teleport(Objects.requireNonNull(ConfigHandler.getData().getLocation("spawn")));
                        p.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You were sent to spawn");
                    }
                    else{
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Spawn isn't set");
                    }
                }).register();
    }
}
