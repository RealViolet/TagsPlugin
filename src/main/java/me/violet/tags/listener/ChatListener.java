package me.violet.tags.listener;

import me.violet.tags.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author Violet (violet@frozen.gg)
 * 12/14/2020 / 10:25 PM
 * Tags / me.violet.tags.listener
 */

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Profile profile = Profile.getByPlayer(event.getPlayer());
        event.setFormat((profile.hasTag() ? profile.getTag().getDisplayName() : "")
                + ChatColor.RESET + "%1$s"
                + ChatColor.GRAY + ":" + ChatColor.WHITE + " %2$s");

    }

}
