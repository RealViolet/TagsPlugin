package me.violet.tags.commands.arguments;

import com.sun.jmx.snmp.SnmpUnknownAccContrModelException;
import me.violet.tags.TagsPlugin;
import me.violet.tags.tag.Tag;
import me.violet.tags.utils.CC;
import me.violet.tags.utils.command.CommandInfo;
import me.violet.tags.utils.command.argument.CommandArgument;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandInfo(names = "create", permission = "violet.tag.create", usage = "create <name> <tag>", description = "Create a tag")
public class TagsCreateArgument extends CommandArgument {

    private final TagsPlugin instance;

    public TagsCreateArgument(TagsPlugin instance) {
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "/tag create <name> <tag>");
            return;
        }

        if (Tag.exists(args[1])) {
            sender.sendMessage(ChatColor.RED + "That tag already exists.");
            return;
        }

        Tag tag = Tag.getByName(args[1]);
        tag.setDisplayName(StringUtils.join(args, " ", 2, args.length));
        Tag.saveTag(tag);
        Tag.TAG_MAP.put(tag.getName(), tag);
        sender.sendMessage(CC.GREEN + "You have created the " + tag.getName() + " tag!");
    }
}