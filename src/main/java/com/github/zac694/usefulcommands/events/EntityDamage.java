package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.ConfigHandler;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
    @EventHandler
    public void onEntityDamage(org.bukkit.event.entity.EntityDamageEvent event){
        if(!event.isCancelled() && ConfigHandler.getData().getBoolean("god." + event.getEntity().getUniqueId())){
            event.setCancelled(true);
        }
    }
}
