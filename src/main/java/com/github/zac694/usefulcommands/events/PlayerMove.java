package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.io.File;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
        File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
        YamlConfiguration data = ConfigHandler.getConfig(dataFile);
        YamlConfiguration config = ConfigHandler.getConfig(configFile);
        if(!event.isCancelled() && data.getBoolean("frozen." + event.getPlayer().getUniqueId())){
            event.getPlayer().sendMessage(config.getString("OutputPrefix") + "§cYou are frozen");
            event.setCancelled(true);
        }
    }
}
