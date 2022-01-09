package com.github.zac694.usefulcommands;

import com.github.zac694.usefulcommands.commands.*;
import com.github.zac694.usefulcommands.events.*;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class UsefulCommands extends JavaPlugin {
    @Override
    public void onEnable() {
        ConfigHandler.setup();
        mainClass = this;
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
        EnderChest.register();
        Ping.register();
        Vanish.register();
        SetSpawn.register();
        Spawn.register();
        Tempban.register();
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
    }
    public void onLoad(){
        CommandAPI.onLoad(new CommandAPIConfig());
    }
    @Override
    public void onDisable() {

    }
    private static UsefulCommands mainClass;
    public static UsefulCommands getMainClass() { return mainClass; }
}
