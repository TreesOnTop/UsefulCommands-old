package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Vanish {
    public static void register(){
        new CommandAPICommand("vanish")
                .withAliases("v")
                .withPermission("usefulcommands.vanish")
                .executesPlayer((sender, args) -> {
                    vanishing(sender);
                }).register();
        new CommandAPICommand("vanish")
                .withAliases("v")
                .withArguments(new PlayerArgument("target").withPermission("usefulcommands.vanish.others"))
                .executes((sender, args) -> {
                    vanishing((Player) args[0]);
                }).register();
    }

    private static void vanishing(Player target) {
        List<String> vanish = new ArrayList<>(ConfigHandler.getConfig().getStringList("vanish"));
        if (ConfigHandler.getData().getStringList("vanish").contains(target.getUniqueId().toString())) {
            Bukkit.getOnlinePlayers().forEach(player -> player.showPlayer(UsefulCommands.getMainClass(), target));
            vanish.remove(target.getUniqueId().toString());
            target.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are no longer in vanish");
        } else {
            Bukkit.getOnlinePlayers().forEach(player -> player.hidePlayer(UsefulCommands.getMainClass(), target));
            vanish.add(target.getUniqueId().toString());
            target.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are now in vanish");
        }
        ConfigHandler.getData().set("vanish", vanish);
        ConfigHandler.save();
    }
}
