package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

public class God {
    public static void register(){
        new CommandAPICommand("god")
                .withArguments(new PlayerArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.god.others"))
                .executes((sender, args) -> {
                    if(ConfigHandler.getData().getBoolean("god." + ((Player)args[0]).getUniqueId())) {
                        ConfigHandler.getData().set("god." + ((Player)args[0]).getUniqueId(), null);
                        ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are no longer in god mode");
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " is no longer god mode");
                        return;
                    }
                    ConfigHandler.getData().set("god." + ((Player)args[0]).getUniqueId(), true);
                    ConfigHandler.save();
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " is now in god mode");
                    ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are now in god mode");
                }).register();
        new CommandAPICommand("god")
                .withPermission(CommandPermission.fromString("usefulcommands.god"))
                .executesPlayer((sender, args) -> {
                    if(ConfigHandler.getData().getBoolean("god." + sender.getUniqueId())) {
                        ConfigHandler.getData().set("god." + sender.getUniqueId(), null);
                        ConfigHandler.save();
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are no longer in god mode");
                        return;
                    }
                    ConfigHandler.getData().set("god." + sender.getUniqueId(), true);
                    ConfigHandler.save();
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are now in god mode");
                }).register();
    }
}