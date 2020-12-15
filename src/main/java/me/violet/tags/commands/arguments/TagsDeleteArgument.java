package me.violet.tags.commands.arguments;

import lombok.RequiredArgsConstructor;
import me.violet.tags.TagsPlugin;
import me.violet.tags.tag.Tag;
import me.violet.tags.utils.CC;
import me.violet.tags.utils.command.CommandInfo;
import me.violet.tags.utils.command.argument.CommandArgument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author Violet (violet@frozen.gg)
 * 12/14/2020 / 10:30 PM
 * Tags / me.violet.tags.commands.arguments
 */
@RequiredArgsConstructor
@CommandInfo(names = "delete", permission = "violet.tags.delete", usage = "delete <tag>", description = "Deletes a tag")
public class TagsDeleteArgument extends CommandArgument {

    private final TagsPlugin instance;

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(CC.RED + "/tag delete <tag>");
            return;
        }
        if (!Tag.exists(args[1])) {
            sender.sendMessage(CC.RED + "This tag does not exist.");
            return;
        }
        Tag tag = Tag.getByName(args[1]);
        tag.delete();
    }
}
