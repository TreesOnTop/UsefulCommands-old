package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.util.Util;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Unmute {
    public static void register(){
        new CommandAPICommand("unmute")
                .withPermission(CommandPermission.fromString("usefulcommands.mute"))
                .withArguments(new PlayerArgument("target"))
                .executes((sender, args) -> {
                    FileConfiguration config = Util.main().getConfig("muted.yml");
                    Player p = (Player) args[0];

                    if(config.getBoolean("muted." + p.getUniqueId())
                            || config.contains("tmuted." + p.getUniqueId())){
                        config.set("muted." + p.getUniqueId(), null);
                        config.set("tmuted." + p.getUniqueId(), null);
                        Util.reloadConfig("muted.yml");

                        sender.sendMessage(Util.outputPrefix() + p.getName() + " is no longer muted");
                        p.sendMessage(Util.outputPrefix() + "You are no longer muted");
                    } else {
                        sender.sendMessage(Util.outputPrefix() + p.getName() + " was not muted");
                    }
                }).register();
    }
}
