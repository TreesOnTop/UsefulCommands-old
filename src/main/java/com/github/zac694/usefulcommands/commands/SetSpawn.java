package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.DoubleArgument;
import dev.jorel.commandapi.arguments.FloatArgument;
import dev.jorel.commandapi.arguments.StringArgument;

public class SetSpawn {
    public static void register(){
        new CommandAPICommand("setspawn")
                .withPermission("usefulcommands.setspawn")
                .executesPlayer((sender, args) -> {
                    ConfigHandler.getData().set("spawn.world", sender.getWorld().getName());
                    ConfigHandler.getData().set("spawn.x", sender.getLocation().getBlockX()+0.5);
                    ConfigHandler.getData().set("spawn.y", sender.getLocation().getBlockY());
                    ConfigHandler.getData().set("spawn.z", sender.getLocation().getBlockZ()+0.5);
                    int yaw = Math.round(sender.getLocation().getYaw());
                    if(yaw <= 45 && yaw >= -45){
                        ConfigHandler.getData().set("spawn.yaw", 0);
                    }else if(yaw >= -135 && yaw <= -45){
                        ConfigHandler.getData().set("spawn.yaw", -90);
                    }else if(yaw <= 135 && yaw >= 45){
                        ConfigHandler.getData().set("spawn.yaw", 90);
                    }else{
                        ConfigHandler.getData().set("spawn.yaw", 180);
                    }
                    ConfigHandler.getData().set("spawn.pitch", 0);
                    ConfigHandler.save();
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Set spawn to " + sender.getLocation().getBlockX() + ", " + sender.getLocation().getBlockY() + ", " + sender.getLocation().getBlockZ());
                }).register();
        new CommandAPICommand("setspawn")
                .withPermission("usefulcommands.setspawn")
                .withArguments(new DoubleArgument("x"))
                .withArguments(new DoubleArgument("y"))
                .withArguments(new DoubleArgument("z"))
                .withArguments(new StringArgument("world"))
                .executes((sender, args) -> {
                    ConfigHandler.getData().set("spawn.world", args[3]);
                    ConfigHandler.getData().set("spawn.x", (double)args[0]+0.5);
                    ConfigHandler.getData().set("spawn.y", args[1]);
                    ConfigHandler.getData().set("spawn.z", (double)args[2]+0.5);
                    ConfigHandler.getData().set("spawn.yaw", 0);
                    ConfigHandler.getData().set("spawn.pitch", 0);
                    ConfigHandler.save();
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Set spawn to " + args[0] + ", " + args[1] + ", " + args[2] + " in world " + args[3]);
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
                    ConfigHandler.getData().set("spawn.world", args[5]);
                    ConfigHandler.getData().set("spawn.x", (double)args[0]+0.5);
                    ConfigHandler.getData().set("spawn.y", args[1]);
                    ConfigHandler.getData().set("spawn.z", (double)args[2]+0.5);
                    ConfigHandler.getData().set("spawn.yaw", args[3]);
                    ConfigHandler.getData().set("spawn.pitch", args[4]);
                    ConfigHandler.save();
                    sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Set spawn to " + args[0] + ", " + args[1] + ", " + args[2] + " with yaw " + args[3] + " and pitch " + args[4] + " in world " + args[5]);
                }).register();
    }
}
