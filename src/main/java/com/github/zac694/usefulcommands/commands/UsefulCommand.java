package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.executors.CommandExecutor;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class UsefulCommand {
    public static void register(){
        new CommandAPICommand("usefulcommands")
                .withArguments(new StringArgument("target"))
                .withPermission(CommandPermission.fromString("usefulcommands.usefulcommands"))
                .withAliases("uc")
                .executes((sender, args) -> {
                    String string = (String)args[0];
                    switch (string) {
                        case "reload" -> {
                            ConfigHandler.reload();
                            sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Plugin reloaded");
                        }
                        case "resetconfig" -> {
                            ConfigHandler.getConfigFile().delete();
                            ConfigHandler.setup();
                            sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Config reset");
                        }
                        case "resetdata" -> {
                            ConfigHandler.getDataFile().delete();
                            ConfigHandler.setup();
                            sender.sendMessage(ConfigHandler.getConfig().getString("OutputPrefix") + "Data reset");
                        }
                        default -> {
                            sender.sendMessage("§6--- UsefulCommands help ---");
                            sender.sendMessage("/usefulcommands - Show this page");
                            sender.sendMessage("/usefulcommands reload - reloads config");
                            sender.sendMessage("/usefulcommands resetconfig - deletes config file and creates a new one");
                            sender.sendMessage("/usefulcommands resetdata - deletes data file and creates a new one");
                            sender.sendMessage("" + args[0]);
                        }
                    }
                }).register();
        new CommandAPICommand("usefulcommands")
                .withPermission(CommandPermission.fromString("usefulcommands.usefulcommands"))
                .executesPlayer((sender, args) -> {
                    sender.sendMessage("§6--- UsefulCommands help ---");
                    sender.sendMessage("/usefulcommands - Show this help");
                    sender.sendMessage("/usefulcommands reload - reloads config");
                    sender.sendMessage("/usefulcommands resetconfig - deletes config file and creates a new one");
                    sender.sendMessage("/usefulcommands resetdata - deletes data file and creates a new one");
                }).register();
    }
}
