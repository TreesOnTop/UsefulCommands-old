package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.ConfigHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent event){
        if(!event.isCancelled() && ConfigHandler.getData().getBoolean("frozen." + event.getPlayer().getUniqueId())){
            event.getPlayer().sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "§cYou are frozen");
            event.setCancelled(true);
        }
    }
}
