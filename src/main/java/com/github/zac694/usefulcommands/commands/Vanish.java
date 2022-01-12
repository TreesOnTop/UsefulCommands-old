package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
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
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.vanish.others"))
                .executes((sender, args) -> {
                    vanishing((Player) args[0]);
                }).register();
    }

    private static void vanishing(Player target) {
        File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
        File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
        YamlConfiguration data = ConfigHandler.getConfig(dataFile);
        YamlConfiguration config = ConfigHandler.getConfig(configFile);
        List<String> vanish = new ArrayList<>(data.getStringList("vanish"));
        if(data.getStringList("vanish").contains(target.getUniqueId().toString())){
            Bukkit.getOnlinePlayers().forEach(player -> player.showPlayer(UsefulCommands.getMainClass(), target));
            vanish.remove(target.getUniqueId().toString());
            target.sendMessage(config.getString("OutputPrefix") + "You are no longer in vanish");
        }else{
            Bukkit.getOnlinePlayers().forEach(player -> player.hidePlayer(UsefulCommands.getMainClass(), target));
            vanish.add(target.getUniqueId().toString());
            target.sendMessage(config.getString("OutputPrefix") + "You are now in vanish");
        }
        data.set("vanish", vanish);
        ConfigHandler.save(dataFile, data);
    }
}
