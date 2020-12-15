package me.violet.tags.commands.arguments;

import me.violet.tags.TagsPlugin;
import me.violet.tags.tag.Tag;
import me.violet.tags.utils.CC;
import me.violet.tags.utils.command.CommandInfo;
import me.violet.tags.utils.command.argument.CommandArgument;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandInfo(
        names = "list",
        permission = "violet.tags.list",
        usage = "list",
        description = " Lists all the tags"
)
public class TagsListArgument extends CommandArgument {

    private TagsPlugin instance;

    public TagsListArgument(TagsPlugin instance) {
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "/tags list");
            return;
        }
        sender.sendMessage(CC.CHAT_BAR);
        sender.sendMessage(CC.GOLD + CC.BOLD + " Tags");
        Tag.TAG_MAP.values().forEach(tag ->
                sender.sendMessage(CC.GOLD + CC.BOLD + "   » " + CC.WHITE + tag.getName()));
        sender.sendMessage("");
        sender.sendMessage(CC.CHAT_BAR);
    }
}
