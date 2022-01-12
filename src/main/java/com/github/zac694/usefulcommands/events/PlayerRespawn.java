package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.File;
import java.util.Objects;

public class PlayerRespawn implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
        File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
        YamlConfiguration data = ConfigHandler.getConfig(dataFile);
        YamlConfiguration config = ConfigHandler.getConfig(configFile);
        if(data.contains("spawn")){
            if(config.getBoolean("SpawnOverridesBed") || event.getPlayer().getBedSpawnLocation() == null){
                event.setRespawnLocation(Objects.requireNonNull(data.getLocation("spawn")));
            }
        }
    }
}
