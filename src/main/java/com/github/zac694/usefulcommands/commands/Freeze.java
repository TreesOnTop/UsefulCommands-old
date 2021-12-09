package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.util.Util;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Freeze {
    public static void register(){
        new CommandAPICommand("freeze")
                .withPermission(CommandPermission.fromString("usefulcommands.freeze"))
                .withArguments(new PlayerArgument("target"))
                .executes((sender, args) -> {
                    FileConfiguration config = Util.main().getConfig("frozen.yml");
                    Player p = (Player) args[0];
                    if (config.getBoolean(p.getUniqueId().toString())) {
                        config.set(p.getUniqueId().toString(), null);
                        sender.sendMessage(Util.outputPrefix() + p.getName() + " is no longer frozen");
                        p.sendMessage(Util.outputPrefix() + "You are no longer frozen");
                    } else {
                        config.set(p.getUniqueId().toString(), true);
                        sender.sendMessage(Util.outputPrefix() + p.getName() + " is now frozen");
                        p.sendMessage(Util.outputPrefix() + "You are now frozen");
                    }
                    Util.reloadConfig("frozen.yml");
                }).register();
    }
}
