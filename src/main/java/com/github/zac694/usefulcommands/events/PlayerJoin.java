package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(ConfigHandler.getData().contains("spawn") && ConfigHandler.getConfig().getBoolean("SpawnOnJoin")){
            event.getPlayer().teleport(Objects.requireNonNull(ConfigHandler.getData().getLocation("spawn")));
        }
        if(ConfigHandler.getData().getStringList("vanish").contains(event.getPlayer().getUniqueId().toString())){
            Bukkit.getOnlinePlayers().forEach(player -> event.getPlayer().hidePlayer(UsefulCommands.getMainClass(), player));
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(ConfigHandler.getData().getStringList("vanish").contains(player.getUniqueId().toString())) {
                event.getPlayer().hidePlayer(UsefulCommands.getMainClass(), player);
            }
        });
    }
}
