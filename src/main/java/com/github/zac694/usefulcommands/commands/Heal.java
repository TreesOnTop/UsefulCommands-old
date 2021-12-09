package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.util.Util;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;
import java.util.Objects;

import static org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH;

public class Heal {
    public static void register(){
        new CommandAPICommand("heal")
                .withArguments(new PlayerArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.heal.others"))
                .executes((sender, args) -> {
                    ((Player)args[0]).setHealth(Objects.requireNonNull(((Player)args[0])
                            .getAttribute(GENERIC_MAX_HEALTH))
                            .getValue());
                    ((Player)args[0]).sendMessage(Util.outputPrefix() + "You have been healed");
                    sender.sendMessage(Util.outputPrefix() + ((Player)args[0]).getName() + " has been healed");
                }).register();
        new CommandAPICommand("heal")
                .withPermission(CommandPermission.fromString("usefulcommands.heal"))
                .executesPlayer((sender, args) -> {
                    sender.setHealth(Objects.requireNonNull(sender.getAttribute(GENERIC_MAX_HEALTH)).getValue());
                    sender.sendMessage(Util.outputPrefix() + "You have been healed");
                }).register();
    }
}
