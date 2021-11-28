package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

public class Freeze {
    public static void register(){
        new CommandAPICommand("freeze")
                .withPermission(CommandPermission.fromString("usefulcommands.freeze"))
                .withArguments(new PlayerArgument("target"))
                .executes((sender, args) -> {
                    if(ConfigHandler.getData().getBoolean("frozen." + ((Player)args[0]).getUniqueId())){
                        ConfigHandler.getData().set("frozen." + ((Player)args[0]).getUniqueId(), null);
                        ConfigHandler.save();
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " is no longer frozen");
                        ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are no longer frozen");
                        return;
                    }
                    ConfigHandler.getData().set("frozen." + ((Player)args[0]).getUniqueId(), true);
                    ConfigHandler.save();
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " is now frozen");
                    ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are now frozen");
                }).register();
    }
}
