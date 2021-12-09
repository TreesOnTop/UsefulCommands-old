package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.util.Util;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.TimeArgument;

import org.bukkit.entity.Player;

public class Tempmute {
    public static void register(){
        new CommandAPICommand("tempmute")
                .withPermission(CommandPermission.fromString("usefulcommands.tempmute"))
                .withArguments(new PlayerArgument("target"))
                .withArguments(new TimeArgument("time"))
                .executes((sender, args) -> {
                    long untime = (((long)args[1])*50+System.currentTimeMillis());
                    Player p = (Player) args[0];//victim, make the code slighly more readable
                    if(Util.main().getConfig("muted.yml").contains("tmuted." + p.getUniqueId())){
                        sender.sendMessage(Util.main().getConfig().getString("OutputPrefix")
                                + p.getName() + " is already muted");
                        return;
                    }
                    Util.main().getConfig("muted.yml").set("tmuted." + p.getUniqueId(), untime);
                    Util.reloadConfig("muted.yml");

                    sender.sendMessage(Util.main().getConfig().getString("OutputPrefix")
                            + p.getName() + " has been muted for " + args[1]);
                    p.sendMessage(Util.main().getConfig().getString("OutputPrefix")
                            + "You are now muted for " + args[1]);
                }).register();
    }
}
