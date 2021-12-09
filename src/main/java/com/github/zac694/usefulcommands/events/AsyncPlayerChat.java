package com.github.zac694.usefulcommands.events;

import com.github.zac694.usefulcommands.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AsyncPlayerChat implements Listener {
    @EventHandler
    public void onAsyncPlayerChat(org.bukkit.event.player.AsyncPlayerChatEvent event){
        if(!event.isCancelled() && Util.main().getConfig("muted.yml")
                .getBoolean("muted." + event.getPlayer().getUniqueId())
                || !event.isCancelled() && Util.main().getConfig("muted.yml")
                .getLong("tmuted." + event.getPlayer().getUniqueId()) >= System.currentTimeMillis()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Util.outputPrefix() + "§cYou are muted");
        } else if (Util.main().getConfig("muted.yml")
                .getLong("tmuted." + event.getPlayer().getUniqueId()) < System.currentTimeMillis()){
            Util.main().getConfig("muted.yml").set("tmuted." + event.getPlayer().getUniqueId(), null);
            Util.main().reloadConfig("muted.yml");
        }
    }
}
