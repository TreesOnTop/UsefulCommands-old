package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.time.Instant;

public class AsyncPlayerChat implements Listener {
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event){
        File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
        File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
        YamlConfiguration data = ConfigHandler.getConfig(dataFile);
        YamlConfiguration config = ConfigHandler.getConfig(configFile);
        if(!event.isCancelled() && data.getBoolean("muted." + event.getPlayer().getUniqueId()) || !event.isCancelled() && data.getLong("tmuted." + event.getPlayer().getUniqueId()) >= Instant.now().getEpochSecond()){
            event.setCancelled(true);
            event.getPlayer().sendMessage(config.getString("OutputPrefix") + "§cYou are muted");
        }else if(data.getLong("tmuted." + event.getPlayer().getUniqueId()) < Instant.now().getEpochSecond()){
            data.set("tmuted." + event.getPlayer().getUniqueId(), null);
            ConfigHandler.save(dataFile, data);
        }
    }
}
