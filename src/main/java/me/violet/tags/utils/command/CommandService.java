package me.violet.tags.utils.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.stream.Collectors;

public class CommandService {

    private static SimpleCommandMap commandMap;

    static {
        try {
            commandMap = (SimpleCommandMap) ReflectionUtil.getValue(Bukkit.getPluginManager(), true, "commandMap");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean registerPluginCommand(Plugin plugin, String command, String description, String usage, List<String> aliases, CommandExecutor commandExecutor) {
        try {
            PluginCommand pluginCommand = (PluginCommand) ReflectionUtil.instantiateObject(PluginCommand.class, command, plugin);
            pluginCommand.setExecutor(commandExecutor);
            ReflectionUtil.setValue(pluginCommand, pluginCommand.getClass().getSuperclass(), true, "description", description);
            ReflectionUtil.setValue(pluginCommand, pluginCommand.getClass().getSuperclass(), true, "usageMessage", "");
            ReflectionUtil.setValue(pluginCommand, pluginCommand.getClass().getSuperclass(), true, "aliases", aliases.stream().map(String::toLowerCase).collect(Collectors.toList()));
            ReflectionUtil.setValue(pluginCommand, pluginCommand.getClass().getSuperclass(), true, "activeAliases", aliases.stream().map(String::toLowerCase).collect(Collectors.toList()));
            commandMap.register(plugin.getName(), pluginCommand);
            plugin.getLogger().info("Registered command " + command);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean registerPluginCommand(Plugin plugin, String command, String description, String usage, List<String> aliases, CommandExecutor commandExecutor, TabCompleter tabCompleter) {
        try {
            PluginCommand pluginCommand = (PluginCommand) ReflectionUtil.instantiateObject(PluginCommand.class, command, plugin);
            pluginCommand.setExecutor(commandExecutor);
            pluginCommand.setTabCompleter(tabCompleter);
            ReflectionUtil.setValue(pluginCommand, pluginCommand.getClass().getSuperclass(), true, "description", description);
            ReflectionUtil.setValue(pluginCommand, pluginCommand.getClass().getSuperclass(), true, "usageMessage", "");
            ReflectionUtil.setValue(pluginCommand, pluginCommand.getClass().getSuperclass(), true, "aliases", aliases.stream().map(String::toLowerCase).collect(Collectors.toList()));
            ReflectionUtil.setValue(pluginCommand, pluginCommand.getClass().getSuperclass(), true, "activeAliases", aliases.stream().map(String::toLowerCase).collect(Collectors.toList()));
            commandMap.register(plugin.getName(), pluginCommand);
            plugin.getLogger().info("Registered command " + command);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
