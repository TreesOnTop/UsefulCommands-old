package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event){
        Bukkit.getOnlinePlayers().forEach(player -> {if(ConfigHandler.getData().getStringList("vanish").contains(player.getUniqueId().toString())){
            event.getPlayer().hidePlayer(UsefulCommands.getMainClass(), player);
        }});
    }
}
