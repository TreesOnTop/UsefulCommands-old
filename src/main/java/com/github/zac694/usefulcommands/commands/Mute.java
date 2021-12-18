package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

public class Mute {
    public static void register(){
        new CommandAPICommand("mute")
                .withPermission("usefulcommands.mute")
                .withArguments(new PlayerArgument("target"))
                .executes((sender, args) -> {
                    if(ConfigHandler.getData().getBoolean("muted." + ((Player)args[0]).getUniqueId())){
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " is already muted");
                        return;
                    }
                    ConfigHandler.getData().set("muted." + ((Player)args[0]).getUniqueId(), true);
                    ConfigHandler.save();
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " is now muted");
                    ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are now muted");
                }).register();
    }
}
