package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ClearChat {
    public static void register(){
        new CommandAPICommand("clearchat")
                .withPermission("usefulcommands.clearchat")
                .executes((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    for (int x = 0; x < 100; x++) {
                        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(""));
                    }
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage( config.getString("OutputPrefix") + sender.getName() + " cleared the chat"));
                }).register();
    }
}
