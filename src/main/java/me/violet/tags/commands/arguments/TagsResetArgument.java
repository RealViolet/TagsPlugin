package me.violet.tags.commands.arguments;

import lombok.RequiredArgsConstructor;
import me.violet.tags.TagsPlugin;
import me.violet.tags.profile.Profile;
import me.violet.tags.utils.CC;
import me.violet.tags.utils.command.CommandInfo;
import me.violet.tags.utils.command.argument.CommandArgument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Violet (violet@frozen.gg)
 * 12/14/2020 / 10:31 PM
 * Tags / me.violet.tags.commands.arguments
 */
@CommandInfo(names = "reset", permission = "violet.tags.reset", usage = "reset", description = "Reset your tag", playerOnly = true)
@RequiredArgsConstructor
public class TagsResetArgument extends CommandArgument {

    private final TagsPlugin instance;

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Profile profile = Profile.getByPlayer((Player) sender);
        profile.setTag(null);
        sender.sendMessage(CC.GREEN + "You have reset your tag!");
    }
}
