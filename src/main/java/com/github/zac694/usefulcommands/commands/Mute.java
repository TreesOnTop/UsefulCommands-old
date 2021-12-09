package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.util.Util;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Mute {
    public static void register(){
        new CommandAPICommand("mute")
                .withPermission(CommandPermission.fromString("usefulcommands.mute"))
                .withArguments(new PlayerArgument("target"))
                .executes((sender, args) -> {
                    Player p = (Player)args[0];
                    FileConfiguration muteConfig = Util.main().getConfig("muted.yml");
                    if(muteConfig.getBoolean("muted." + p.getUniqueId())){
                        sender.sendMessage(Util.outputPrefix() + p.getName() + " is already muted");
                        return;
                    }
                    muteConfig.set("muted." + p.getUniqueId(), true);
                    Util.reloadConfig("muted.yml");
                    sender.sendMessage(Util.outputPrefix() + p.getName() + " is now muted");
                    p.sendMessage(Util.outputPrefix() + "You are now muted");
                }).register();
    }
}
