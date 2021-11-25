package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ClearChat {
    public static void register(){
        new CommandAPICommand("clearchat")
                .withPermission(CommandPermission.fromString("usefulcommands.clearchat"))
                .executes((sender, args) -> {
                    for (int x = 0; x < 100; x++) {
                        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(""));
                    }
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage( ConfigHandler.getConfig().getString("OutputPrefix") + sender.getName() + "cleared the chat"));
                }).register();
    }
}
