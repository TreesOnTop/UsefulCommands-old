package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent event){
        if(!event.isCancelled() && Util.main().getConfig("frozened.yml")
                .getBoolean(event.getPlayer().getUniqueId().toString())){
            event.getPlayer().sendMessage(Util.outputPrefix() + "§cYou are frozen");
            event.setCancelled(true);
        }
    }
}
