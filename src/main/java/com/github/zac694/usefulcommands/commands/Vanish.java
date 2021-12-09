package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.util.Util;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Vanish {
    public static void register(){
        new CommandAPICommand("vanish")
                .withAliases("v")
                .withArguments(new PlayerArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.vanish.others"))
                .executes((sender, args) -> {
                    vanishing((Player) args[0]);
                }).register();
        new CommandAPICommand("vanish")
                .withAliases("v")
                .withPermission(CommandPermission.fromString("usefulcommands.vanish"))
                .executesPlayer((sender, args) -> {
                    vanishing(sender);
                }).register();
    }

    private static void vanishing(Player target) {
        FileConfiguration vanishConfig = Util.main().getConfig("vanished.yml");
        List<String> vanish = vanishConfig.getStringList("playerlist");
        if (vanish.contains(target.getUniqueId().toString())) {
            Bukkit.getOnlinePlayers().forEach(player -> player.showPlayer(Util.main(), target));
            vanish.remove(target.getUniqueId().toString());
            target.sendMessage(Util.outputPrefix() + "You are no longer in vanish");
        } else {
            Bukkit.getOnlinePlayers().forEach(player -> player.hidePlayer(Util.main(), target));
            vanish.add(target.getUniqueId().toString());
            target.sendMessage(Util.outputPrefix() + "You are now in vanish");
        }
        vanishConfig.set("vanish", vanish);
        Util.reloadConfig("vanished.yml");
    }
}
