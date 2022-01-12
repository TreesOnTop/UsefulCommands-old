package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.File;

public class EntityDamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
        YamlConfiguration data = ConfigHandler.getConfig(dataFile);
        if(!event.isCancelled() && data.getBoolean("god." + event.getEntity().getUniqueId())){
            event.setCancelled(true);
        }
    }
}
