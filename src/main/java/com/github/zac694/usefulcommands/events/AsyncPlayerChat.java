package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.ConfigHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event){
        if(!event.isCancelled() && ConfigHandler.getData().getBoolean("muted." + event.getPlayer().getUniqueId()) || !event.isCancelled() && ConfigHandler.getData().getLong("tmuted." + event.getPlayer().getUniqueId()) >= System.currentTimeMillis()){
            event.setCancelled(true);
            event.getPlayer().sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "§cYou are muted");
            return;
        }
        if(ConfigHandler.getData().getLong("tmuted." + event.getPlayer().getUniqueId()) < System.currentTimeMillis()){
            ConfigHandler.getData().set("tmuted." + event.getPlayer().getUniqueId(), null);
            ConfigHandler.save();
        }
    }
}
