package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Location;
import org.bukkit.World;


public class SetSpawn {
    public static void register(){
        new CommandAPICommand("SetSpawn")
                .withPermission("usefulcommands.setspawn")
                .executesPlayer((sender, args) -> {
                    double x = sender.getLocation().getBlockX()+0.5;
                    double y = sender.getLocation().getBlockY();
                    double z = sender.getLocation().getBlockZ()+0.5;
                    World world = sender.getWorld();
                    int yaw = Math.round(sender.getLocation().getYaw());
                    if(yaw <= 45 && yaw >= -45){
                        ConfigHandler.getData().set("spawn", new Location(world,x,y,z,0,0));
                    }else if(yaw >= -135 && yaw <= -45){
                        ConfigHandler.getData().set("spawn", new Location(world,x,y,z,-90,0));
                    }else if(yaw <= 135 && yaw >= 45){
                        ConfigHandler.getData().set("spawn", new Location(world,x,y,z,90,0));
                    }else{
                        ConfigHandler.getData().set("spawn", new Location(world,x,y,z,180,0));
                    }
                    ConfigHandler.save();
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Set spawn to " + x + ", " + y + ", " + z);
                }).register();
    }
}
