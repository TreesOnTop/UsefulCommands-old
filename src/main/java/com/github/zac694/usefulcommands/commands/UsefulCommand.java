package com.github.zac694.usefulcommands.commands;

import com.github.zac694.usefulcommands.ConfigHandler;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class UsefulCommand {
    public static void register(){
        new CommandAPICommand("usefulcommands")
                .withArguments(new GreedyStringArgument("target"))
                .withPermission("usefulcommands.usefulcommands")
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
                        case "help", "help 1" -> {
                            sender.sendMessage("§6--- UsefulCommands help ---");
                            sender.sendMessage("/broadcast <message> - broadcasts a message to all players");
                            sender.sendMessage("/clearchat - clears the chat");
                            sender.sendMessage("/enderchest [player] - opens the ender chest of a player");
                            sender.sendMessage("/feed [player] - gives a player full hunger");
                            sender.sendMessage("/fly [player] - allows a player to fly");
                            sender.sendMessage("/freeze <player> - stops a player from moving");
                            sender.sendMessage("/gma [player] - sets a player's gamemode to adventure");
                            sender.sendMessage("/gmc [player] - sets a player's gamemode to creative");
                            sender.sendMessage("/uc help 2 for more commands");
                            sender.sendMessage("");
                        }
                        case "help 2" -> {
                            sender.sendMessage("§6--- UsefulCommands help 2 ---");
                            sender.sendMessage("/gms [player] - sets a player's gamemode to survival");
                            sender.sendMessage("/gmsp [player] - sets a player's gamemode to spectator");
                            sender.sendMessage("/god [player] - makes a player unable to take any damage");
                            sender.sendMessage("/heal [player] - heals a player to max health");
                            sender.sendMessage("/inventorysee <player> - allows you to see and edit a players inventory");
                            sender.sendMessage("/mute <player> - mutes a player");
                            sender.sendMessage("/ping [player] - gets a player's ping");
                            sender.sendMessage("/suicide - kills the player");
                            sender.sendMessage("/uc help 3 for more commands");
                            sender.sendMessage("");
                        }
                        case "help 3" -> {
                            sender.sendMessage("§6--- UsefulCommands help 2 ---");
                            sender.sendMessage("/tempmute <player> <time> - temporarily mutes a player");
                            sender.sendMessage("/usefulcommands [text] - command for server managing");
                            sender.sendMessage("/unmute <player> - unmutes a player");
                            sender.sendMessage("/vanish [<player>] - hides a player from everyone on the server");
                            sender.sendMessage("");
                        }
                        default -> {
                            sender.sendMessage("§6--- UsefulCommands help ---");
                            sender.sendMessage("/usefulcommands - Show this page");
                            sender.sendMessage("/usefulcommands reload - reloads config");
                            sender.sendMessage("/usefulcommands resetconfig - deletes config file and creates a new one");
                            sender.sendMessage("/usefulcommands resetdata - deletes data file and creates a new one");
                            sender.sendMessage("/usefulcommands help - shows all commands");
                            sender.sendMessage("");
                        }
                    }
                }).register();
        new CommandAPICommand("usefulcommands")
                .withPermission(CommandPermission.fromString("usefulcommands.usefulcommands"))
                .withAliases("uc")
                .executesPlayer((sender, args) -> {
                    sender.sendMessage("§6--- UsefulCommands help ---");
                    sender.sendMessage("/usefulcommands - Show this help");
                    sender.sendMessage("/usefulcommands reload - reloads config");
                    sender.sendMessage("/usefulcommands resetconfig - deletes config file and creates a new one");
                    sender.sendMessage("/usefulcommands resetdata - deletes data file and creates a new one");
                    sender.sendMessage("/usefulcommands help - shows a list of all commands");
                    sender.sendMessage("");
                }).register();
    }
}
