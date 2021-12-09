package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.util.Util;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class God {
    public static void register(){
        new CommandAPICommand("god")
                .withArguments(new PlayerArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.god.others"))
                .executes((sender, args) -> {
                    FileConfiguration godConfig = Util.main().getConfig("god.yml");
                    Player p = (Player) args[0];
                    if(godConfig.getBoolean(p.getUniqueId().toString())) {
                        godConfig.set("god." + ((Player)args[0]).getUniqueId(), null);
                        Util.reloadConfig("god.yml");
                        p.sendMessage(Util.outputPrefix() + "You are no longer in god mode");
                        sender.sendMessage(Util.outputPrefix() + p.getName() + " is no longer god mode");
                        return;
                    }
                    godConfig.set(p.getUniqueId().toString(), true);
                    Util.reloadConfig("god.yml");
                    sender.sendMessage(Util.outputPrefix() + p.getName() + " is now in god mode");
                    p.sendMessage(Util.outputPrefix() + "You are now in god mode");
                }).register();

        new CommandAPICommand("god")
                .withPermission(CommandPermission.fromString("usefulcommands.god"))
                .executesPlayer((sender, args) -> {
                    FileConfiguration godConfig = Util.main().getConfig("god.yml");
                    if(godConfig.getBoolean(sender.getUniqueId().toString())) {
                        godConfig.set("god." + ((Player)args[0]).getUniqueId(), null);
                        Util.reloadConfig("god.yml");
                        sender.sendMessage(Util.outputPrefix() + "You are no longer in god mode");
                        return;
                    }
                    godConfig.set(sender.getUniqueId().toString(), true);
                    Util.reloadConfig("god.yml");
                    sender.sendMessage(Util.outputPrefix() + sender.getName() + "You are now in god mode");
                }).register();
    }
}