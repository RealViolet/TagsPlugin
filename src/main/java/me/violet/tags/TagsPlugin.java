package me.violet.tags;

import lombok.Getter;
import me.violet.tags.commands.TagCommand;
import me.violet.tags.listener.ChatListener;
import me.violet.tags.mongo.MongoManager;
import me.violet.tags.profile.Profile;
import me.violet.tags.profile.ProfileListener;
import me.violet.tags.tag.Tag;
import me.violet.tags.utils.menu.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TagsPlugin extends JavaPlugin {

    @Getter
    private static TagsPlugin instance;

    @Getter
    private MongoManager mongoManager;

    @Getter
    private ExecutorService executorService;

    @Override
    public void onEnable() {
        instance = this;
        mongoManager = new MongoManager();
        new TagCommand(this);
        Tag.loadTags();
        executorService = Executors.newFixedThreadPool(4);

        Bukkit.getPluginManager().registerEvents(new ProfileListener(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

    }

    @Override
    public void onDisable() {
        Tag.saveTags();
        Bukkit.getOnlinePlayers().stream()
                .map(Profile::getByPlayer)
                .forEach(Profile::save);
    }
}
