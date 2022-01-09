package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.ConfigHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Objects;

public class PlayerRespawn implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        if(ConfigHandler.getData().contains("spawn")){
            if (ConfigHandler.getConfig().getBoolean("SpawnOverridesBed") || event.getPlayer().getBedSpawnLocation() == null) {
                event.setRespawnLocation(Objects.requireNonNull(ConfigHandler.getData().getLocation("spawn")));
            }
        }
    }
}
