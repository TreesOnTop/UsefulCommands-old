package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Vanish {
    public static void register(){
        new CommandAPICommand("vanish")
                .withAliases("v")
                .withArguments(new PlayerArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.vanish.others"))
                .executes((sender, args) -> {
                    List<String> vanish = new ArrayList<>(ConfigHandler.getConfig().getStringList("vanish"));
                    if(ConfigHandler.getData().getStringList("vanish").contains(((Player)args[0]).getUniqueId().toString())){
                        Bukkit.getOnlinePlayers().forEach(player -> player.showPlayer(UsefulCommands.getMainClass(), (Player)args[0]));
                        vanish.remove(((Player)args[0]).getUniqueId().toString());
                        ((Player)args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are no longer in vanish");
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player)args[0]).getName() + " is no longer vanish");
                    }else {
                        Bukkit.getOnlinePlayers().forEach(player -> player.hidePlayer(UsefulCommands.getMainClass(), (Player) args[0]));
                        vanish.add(((Player) args[0]).getUniqueId().toString());
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + ((Player) args[0]).getName() + " is now in vanish");
                        ((Player) args[0]).sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are now in vanish");
                    }
                    ConfigHandler.getData().set("vanish", vanish);
                    ConfigHandler.save();
                }).register();
        new CommandAPICommand("vanish")
                .withAliases("v")
                .withPermission(CommandPermission.fromString("usefulcommands.vanish"))
                .executesPlayer((sender, args) -> {
                    List<String> vanish = new ArrayList<>(ConfigHandler.getConfig().getStringList("vanish"));
                    if(ConfigHandler.getData().getStringList("vanish").contains(sender.getUniqueId().toString())){
                        Bukkit.getOnlinePlayers().forEach(player -> player.showPlayer(UsefulCommands.getMainClass(), sender));
                        vanish.remove(sender.getUniqueId().toString());
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are no longer in vanish");
                    }else {
                        Bukkit.getOnlinePlayers().forEach(player -> player.hidePlayer(UsefulCommands.getMainClass(), sender));
                        vanish.add(sender.getUniqueId().toString());
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You are now in vanish");
                    }
                    ConfigHandler.getData().set("vanish", vanish);
                    ConfigHandler.save();
                }).register();
    }
}
