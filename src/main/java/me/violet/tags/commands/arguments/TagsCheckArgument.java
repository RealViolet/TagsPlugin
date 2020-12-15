package me.violet.tags.commands.arguments;

import me.violet.tags.TagsPlugin;
import me.violet.tags.profile.Profile;
import me.violet.tags.utils.command.CommandInfo;
import me.violet.tags.utils.command.argument.CommandArgument;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(names = "check", permission = "violet.tags.check", playerOnly = true, usage = "check", description = "Shows which tag you have selected")
public class TagsCheckArgument extends CommandArgument {

    private TagsPlugin instance;

    public TagsCheckArgument(TagsPlugin instance) {
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Profile profile = Profile.getByPlayer(player);
        if (profile.getTag() != null)
            player.sendMessage(ChatColor.GREEN + "Your current tag is \"" + profile.getTag().getDisplayName() + "\".");
        else
            player.sendMessage(ChatColor.RED + "You do not have any tag selected!");
    }
}
