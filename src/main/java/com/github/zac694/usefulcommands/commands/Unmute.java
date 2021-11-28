package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

public class Unmute {
    public static void register(){
        new CommandAPICommand("unmute")
                .withPermission(CommandPermission.fromString("usefulcommands.mute"))
                .withArguments(new PlayerArgument("target"))
                .executes((sender, args) -> {
                    if(ConfigHandler.getData().getBoolean("muted." + ((Player)args[0]).getUniqueId()) || ConfigHandler.getData().contains("tmuted." + ((Player)args[0]).getUniqueId())){
                        ConfigHandler.getData().set("muted." + ((Player)args[0]).getUniqueId(), null);
                        ConfigHandler.getData().set("tmuted." + ((Player)args[0]).getUniqueId(), null);
                        ConfigHandler.save();
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " is no longer muted");
                        ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are no longer muted");
                        return;
                    }
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " was not muted");
                }).register();
    }
}
