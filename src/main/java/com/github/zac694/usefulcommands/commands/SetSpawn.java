package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import com.github.zac694.usefulcommands.UsefulCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.DoubleArgument;
import dev.jorel.commandapi.arguments.FloatArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class SetSpawn {
    public static void register(){
        new CommandAPICommand("setspawn")
                .withPermission("usefulcommands.setspawn")
                .executesPlayer((sender, args) -> {
                    File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration data = ConfigHandler.getConfig(dataFile);
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    double x = sender.getLocation().getBlockX()+0.5;
                    double y = sender.getLocation().getBlockY();
                    double z = sender.getLocation().getBlockZ()+0.5;
                    World world = sender.getWorld();
                    int yaw = Math.round(sender.getLocation().getYaw());
                    if(yaw <= 45 && yaw >= -45){
                        data.set("spawn", new Location(world,x,y,z,0,0));
                    }else if(yaw >= -135 && yaw <= -45){
                        data.set("spawn", new Location(world,x,y,z,-90,0));
                    }else if(yaw <= 135 && yaw >= 45){
                        data.set("spawn", new Location(world,x,y,z,90,0));
                    }else{
                        data.set("spawn", new Location(world,x,y,z,180,0));
                    }
                    ConfigHandler.save(dataFile, data);
                    sender.sendMessage(config.getString("OutputPrefix") + "Set spawn to " + x + ", " + y + ", " + z);
                }).register();
        new CommandAPICommand("setspawn")
                .withPermission("usefulcommands.setspawn")
                .withArguments(new DoubleArgument("x"))
                .withArguments(new DoubleArgument("y"))
                .withArguments(new DoubleArgument("z"))
                .withArguments(new StringArgument("world"))
                .executes((sender, args) -> {
                    File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration data = ConfigHandler.getConfig(dataFile);
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    double x = (double)args[0]+0.5;
                    double z = (double)args[2]+0.5;
                    data.set("spawn", new Location(Bukkit.getServer().getWorld(args[3].toString()),x,(double)args[1],z,0,0));
                    ConfigHandler.save(dataFile, data);
                    sender.sendMessage(config.getString("OutputPrefix") + "Set spawn to " + args[0] + ", " + args[1] + ", " + args[2] + " in world " + args[3]);
                }).register();
        new CommandAPICommand("setspawn")
                .withPermission("usefulcommands.setspawn")
                .withArguments(new DoubleArgument("x"))
                .withArguments(new DoubleArgument("y"))
                .withArguments(new DoubleArgument("z"))
                .withArguments(new FloatArgument("yaw"))
                .withArguments(new FloatArgument("pitch"))
                .withArguments(new StringArgument("world"))
                .executes((sender, args) -> {
                    File dataFile = ConfigHandler.fileInit("data.yml", UsefulCommands.getMainClass());
                    File configFile = ConfigHandler.fileInit("config.yml", UsefulCommands.getMainClass());
                    YamlConfiguration data = ConfigHandler.getConfig(dataFile);
                    YamlConfiguration config = ConfigHandler.getConfig(configFile);
                    double x = (double)args[0]+0.5;
                    double z = (double)args[2]+0.5;
                    data.set("spawn", new Location(Bukkit.getServer().getWorld(args[5].toString()),x,(double)args[1],z,(float)args[3],(float)args[4]));
                    ConfigHandler.save(dataFile, data);
                    sender.sendMessage(config.getString("OutputPrefix") + "Set spawn to " + args[0] + ", " + args[1] + ", " + args[2] + " with yaw " + args[3] + " and pitch " + args[4] + " in world " + args[5]);
                }).register();
    }
}
