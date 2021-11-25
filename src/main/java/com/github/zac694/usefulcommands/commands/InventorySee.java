package com.github.zac694.usefulcommands.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

public class InventorySee {
    public static void register(){
        new CommandAPICommand("inventorysee")
                .withPermission(CommandPermission.fromString("usefulcommands.invsee"))
                .withArguments(new PlayerArgument("target"))
                .withAliases("invsee", "seeinv")
                .executesPlayer((sender, args) -> {
                    sender.openInventory(((Player)args[0]).getInventory());
                }).register();
    }
}