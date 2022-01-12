package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.Objects;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
        File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
        YamlConfiguration data = ConfigHandler.getConfig(dataFile);
        YamlConfiguration config = ConfigHandler.getConfig(configFile);
        if(data.contains("spawn") && config.getBoolean("SpawnOnJoin")){
            event.getPlayer().teleport(Objects.requireNonNull(data.getLocation("spawn")));
        }
        if(data.getStringList("vanish").contains(event.getPlayer().getUniqueId().toString())){
            Bukkit.getOnlinePlayers().forEach(player -> event.getPlayer().hidePlayer(UsefulCommands.getMainClass(), player));
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(data.getStringList("vanish").contains(player.getUniqueId().toString())) {
                event.getPlayer().hidePlayer(UsefulCommands.getMainClass(), player);
            }
        });
    }
}
