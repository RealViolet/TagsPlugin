package me.violet.tags.utils.command;

import me.violet.tags.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public abstract class CommandWrapper implements CommandExecutor, TabCompleter {

    private Plugin plugin;
    private CommandInfo commandInfo;
    private CommandCompleter commandCompleter;

    public abstract void execute(CommandSender sender, Command command, String label, String[] args);

    public CommandWrapper(Plugin plugin) {
        this.plugin = plugin;
        this.register();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!canUse(sender)) {
            sender.sendMessage(CC.RED + "You don't have permissions to execute this command");
            return true;
        }
        execute(sender, command, label, args);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (this.commandCompleter != null) {
            List<String> completions = commandCompleter.tabComplete(sender, args);
            if ((completions == null) || (completions.isEmpty())) {
                return null;
            }

            return getCompletions(args, completions);
        }
        return null;
    }

    private void register() {
        if (this.plugin != null) {
            if (this.getClass().isAnnotationPresent(CommandInfo.class)) {
                this.commandInfo = this.getClass().getAnnotation(CommandInfo.class);
                String label;
                List<String> aliases = new ArrayList<>();
                if (this.commandInfo.names().length > 1) {
                    label = this.commandInfo.names()[0].toLowerCase();
                    for (String name : this.commandInfo.names()) {
                        if (!name.toLowerCase().equals(label)) {
                            aliases.add(name.toLowerCase());
                        }
                    }
                } else {
                    label = this.commandInfo.names()[0];
                }

                if (this instanceof CommandCompleter) {
                    this.commandCompleter = (CommandCompleter) this;
                    CommandService.registerPluginCommand(plugin, label, this.commandInfo.description(), this.commandInfo.usage(), aliases, this, this);
                } else {
                    CommandService.registerPluginCommand(plugin, label, this.commandInfo.description(), this.commandInfo.usage(), aliases, this);
                }
            }
        }
    }

    public void sync(Runnable runnable) {
        if (this.commandInfo.async()) {
            Bukkit.getScheduler().runTask(plugin, runnable);
        }
    }

    private boolean canUse(CommandSender sender) {
        switch (this.commandInfo.permission()) {
            case "":
                return true;
            case "op":
                return sender.isOp();
            case "console":
                return sender instanceof ConsoleCommandSender;
            case "player":
                return sender instanceof Player;
            default:
                if (this.commandInfo.playerOnly()) {
                    return (sender instanceof Player) && (sender.hasPermission(this.commandInfo.permission()));
                }
                return sender.hasPermission(this.commandInfo.permission());
        }
    }

    public CommandInfo getCommandInfo() {
        return this.commandInfo;
    }

    public List<String> getCompletions(String[] args, List<String> input) {
        String argument = args[args.length - 1];
        return input.stream().filter(string -> string.regionMatches(true, 0, argument, 0, argument.length())).limit(80).collect(Collectors.toList());
    }
}
