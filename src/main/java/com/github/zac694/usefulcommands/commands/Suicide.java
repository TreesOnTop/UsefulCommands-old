package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Suicide {
    public static void register(){
        new CommandAPICommand("suicide")
                .withPermission("usefulcommands.suicide")
                .executesPlayer((sender, args) -> {
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    sender.setHealth(0);
                    sender.sendMessage(config.getString("OutputPrefix") + "You died");
                }).register();
    }
}
