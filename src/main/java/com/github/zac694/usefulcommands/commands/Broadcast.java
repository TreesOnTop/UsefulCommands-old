package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class Broadcast {
    public static void register(){
        new CommandAPICommand("broadcast")
                .withPermission("usefulcommands.broadcast")
                .withArguments(new GreedyStringArgument("message"))
                .withAliases("bc")
                .executes((sender, args) -> {
                    List<String> broadcast = new ArrayList<>(ConfigHandler.getConfig().getStringList("Broadcast"));
                    for (int x = 0; x < broadcast.size(); x++) {
                        int value = x;
                        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(broadcast.get(value).replace("<broadcast-message>", (String)args[0]).replace("<broadcast-sender>", sender.getName()) + ""));
                    }
                }).register();
    }
}
