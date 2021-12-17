package com.github.zac694.usefulcommands.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

public class EnderChest {
    public static void register(){
        new CommandAPICommand("enderchest")
            .withPermission(CommandPermission.fromString("usefulcommands.enderchest"))
            .withAliases("ec", "echest")
            .executesPlayer((sender, args) -> {
                sender.openInventory(sender.getEnderChest());
            }).register();
        new CommandAPICommand("enderchest")
                .withArguments(new PlayerArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.enderchest.others"))
                .withAliases("ec", "echest")
                .executesPlayer((sender, args) -> {
                    sender.openInventory(((Player)args[0]).getEnderChest());
                }).register();

    }
}
