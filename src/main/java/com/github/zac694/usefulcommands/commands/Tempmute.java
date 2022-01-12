package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LongArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.time.Instant;

public class Tempmute {
    public static void register(){
        new CommandAPICommand("tempmute")
                .withPermission("usefulcommands.tempmute")
                .withArguments(new PlayerArgument("player"))
                .withArguments(new LongArgument("time"))
                .withArguments(new StringArgument("timeFormat").replaceSuggestions(suggestions ->
                        new String[] {"s", "m", "h", "d"}
                ))
                .executes((sender, args) -> {
                    File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration data = ConfigHandler.getConfig(dataFile);
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    long sec = switch (args[2].toString().toLowerCase()) {
                        case "minutes", "min", "m" -> (long) args[1] * 60;
                        case "hours", "h" -> (long) args[1] * 3600;
                        case "days", "d" -> (long) args[1] * 86400;
                        default -> (long) args[1];
                    };
                    if(data.contains("tmuted." + ((Player)args[0]).getUniqueId())){
                        sender.sendMessage(config.getString("OutputPrefix") + ((Player)args[0]).getName() + " is already muted");
                    }else{
                        data.set("tmuted." + ((Player) args[0]).getUniqueId(), sec + Instant.now().getEpochSecond());
                        ConfigHandler.save(dataFile, data);
                        sender.sendMessage(config.getString("OutputPrefix") + ((Player) args[0]).getName() + " has been muted for " + args[1] + args[2]);
                        ((Player) args[0]).sendMessage(config.getString("OutputPrefix") + "You are now muted for " + args[1] + args[2]);
                    }
                }).register();
    }
}
