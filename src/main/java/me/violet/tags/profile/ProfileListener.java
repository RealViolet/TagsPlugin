package me.violet.tags.profile;

import me.violet.tags.TagsPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TagsPlugin.getInstance().getExecutorService().execute(() -> {
            Profile profile = Profile.getByPlayer(player);
            profile.load();
            System.out.println("asdasdasd");
        });
        System.out.println("Loaded after executor service was ran");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        TagsPlugin.getInstance().getExecutorService().execute(() -> {
            Profile.getProfiles().get(player.getUniqueId()).save();
        });
    }


}
