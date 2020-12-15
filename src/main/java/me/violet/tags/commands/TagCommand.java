package me.violet.tags.commands;

import me.violet.tags.TagsPlugin;
import me.violet.tags.commands.arguments.*;
import me.violet.tags.utils.command.CommandInfo;
import me.violet.tags.utils.command.argument.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
@CommandInfo(names = "tag", permission = "violet.tags", helpTitle = "Tags", playerOnly = true)
public class TagCommand extends CommandExecutor {

    private TagsPlugin instance;

    public TagCommand(TagsPlugin instance) {
        super(instance);
        this.instance = instance;
        this.addArgument(new TagsCreateArgument(instance));
        this.addArgument(new TagsListArgument(instance));
        this.addArgument(new TagsSelectArgument(instance));
        this.addArgument(new TagsCheckArgument(instance));
        this.addArgument(new TagsDeleteArgument(instance));
        this.addArgument(new TagsResetArgument(instance));
    }

    @Override
    public boolean executeOther(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
