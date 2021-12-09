package com.github.zac694.usefulcommands;

import com.github.zac694.usefulcommands.commands.register.Register;
import com.github.zac694.usefulcommands.util.MultiConfigJavaPlugin;
import com.github.zac694.usefulcommands.events.AsyncPlayerChat;
import com.github.zac694.usefulcommands.events.EntityDamage;
import com.github.zac694.usefulcommands.events.PlayerMove;
import com.github.zac694.usefulcommands.util.Util;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;

public final class UsefulCommands extends MultiConfigJavaPlugin {
    @Override
    public void onEnable() {
        Util.setMain(this);
        CommandAPI.onEnable(this);

        //registering all the commands
        Register.register(); //moved to clean up main class

        //register all the config, then do the... thing
        Util.registerConfig("config.yml");
        Util.registerConfig("vanished.yml");
        Util.reloadAllConfig(); //here is the... thing

        //registering all the event handlers
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
    }
   
    @Override
    public void onLoad(){
        CommandAPI.onLoad(new CommandAPIConfig());
    }
}
