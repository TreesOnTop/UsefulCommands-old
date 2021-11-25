package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;

public class Suicide {
    public static void register(){
        new CommandAPICommand("suicide")
                .withPermission(CommandPermission.fromString("usefulcommands.suicide"))
                .executesPlayer((sender, args) -> {
                    sender.setHealth(0);
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You died");
                }).register();
    }
}
