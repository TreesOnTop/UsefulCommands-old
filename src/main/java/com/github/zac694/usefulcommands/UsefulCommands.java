package com.github.zac694.usefulcommands;

import com.github.zac694.usefulcommands.commands.*;
import com.github.zac694.usefulcommands.events.AsyncPlayerChat;
import com.github.zac694.usefulcommands.events.EntityDamage;
import com.github.zac694.usefulcommands.events.PlayerMove;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class UsefulCommands extends JavaPlugin {
    @Override
    public void onEnable() {
        ConfigHandler.setup();
        CommandAPI.onEnable(this);
        Fly.register();
        Heal.register();
        Feed.register();
        Suicide.register();
        InventorySee.register();
        Gamemode.register();
        Broadcast.register();
        ClearChat.register();
        God.register();
        UsefulCommand.register();
        Freeze.register();
        Mute.register();
        Unmute.register();
        Tempmute.register();
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
    }
    public void onLoad(){
        CommandAPI.onLoad(new CommandAPIConfig());
    }
    @Override
    public void onDisable() {

    }
}
