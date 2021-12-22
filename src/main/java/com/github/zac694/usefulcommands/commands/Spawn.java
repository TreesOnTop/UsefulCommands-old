package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Spawn{
    public static void register(){
        new CommandAPICommand("spawn")
                .withPermission("usefulcommands.spawn")
                .executesPlayer((sender, args) -> {
                    if(ConfigHandler.getData().contains("spawn")){
                        sender.sendMessage("test");
                        teleport(sender);
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Sent you to spawn");
                    }
                    else{
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Spawn isn't set");
                    }
                }).register();
        new CommandAPICommand("spawn")
                .withArguments(new PlayerArgument("player").withPermission("usefulcommands.spawn.others"))
                .executes((sender, args) -> {
                    if(ConfigHandler.getData().contains("spawn")){
                        Player p = (Player)args[0];
                        teleport(p);
                        p.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "You were sent to spawn");
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Sent" + p.getName() + "to spawn");
                    }
                    else{
                        sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Spawn isn't set");
                    }
                }).register();
    }

    private static void teleport(Player p) {
        World w = Bukkit.getServer().getWorld(Objects.requireNonNull(ConfigHandler.getData().getString("spawn.world")));
        double x = ConfigHandler.getData().getDouble("spawn.x");
        double y = ConfigHandler.getData().getDouble("spawn.y");
        double z = ConfigHandler.getData().getDouble("spawn.z");
        float yaw = (float)ConfigHandler.getData().getDouble("spawn.yaw");
        float pitch = (float)ConfigHandler.getData().getDouble("spawn.pitch");
        Location loc = new Location(w, x, y, z, yaw, pitch);
        p.teleport(loc);
    }
}
