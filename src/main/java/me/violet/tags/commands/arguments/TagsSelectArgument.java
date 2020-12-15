package me.violet.tags.commands.arguments;

import me.violet.tags.TagsPlugin;
import me.violet.tags.menu.TagsMenu;
import me.violet.tags.profile.Profile;
import me.violet.tags.tag.Tag;
import me.violet.tags.utils.command.CommandInfo;
import me.violet.tags.utils.command.argument.CommandArgument;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(names = "select", permission = "violet.tags.select", usage = "select", description = "Select a tag", playerOnly = true)
public class TagsSelectArgument extends CommandArgument {

    private TagsPlugin instance;

    public TagsSelectArgument(TagsPlugin instance) {
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "/tag select");
            return;
        }
        new TagsMenu().openMenu(player);
    }
}
