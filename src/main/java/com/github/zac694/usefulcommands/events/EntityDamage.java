package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if(!event.isCancelled() && Util.main().getConfig("god.yml")
                .getBoolean(event.getEntity().getUniqueId().toString())){
            event.setCancelled(true);
        }
    }
}
