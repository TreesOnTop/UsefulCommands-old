package com.github.zac694.usefulcommands.functions;

import com.github.zac694.usefulcommands.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;

public class GetLocation {
    public static Location getLoc(String s) {
        World w = Bukkit.getServer().getWorld(Objects.requireNonNull(ConfigHandler.getData().getString(s + ".world")));
        double x = ConfigHandler.getData().getDouble(s + ".x");
        double y = ConfigHandler.getData().getDouble(s + ".y");
        double z = ConfigHandler.getData().getDouble(s + ".z");
        float yaw = (float)ConfigHandler.getData().getDouble(s + ".yaw");
        float pitch = (float)ConfigHandler.getData().getDouble(s + ".pitch");
        return new Location(w, x, y, z, yaw, pitch);
    }
}
