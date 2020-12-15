package me.violet.tags.menu;

import me.violet.tags.profile.Profile;
import me.violet.tags.tag.Tag;
import me.violet.tags.utils.CC;
import me.violet.tags.utils.ItemBuilder;
import me.violet.tags.utils.menu.Button;
import me.violet.tags.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class TagsMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.GOLD + "Select a Tag!";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        Tag.TAG_MAP.values().forEach(tag -> buttons.put(buttons.size(), new Button() {
            @Override
            public ItemStack getItem(Player player) {
                if (!player.hasPermission("violet.tags." + tag.getName())) {
                    return new ItemBuilder(Material.NAME_TAG).setDisplayName(tag.getDisplayName()).setLore(
                            CC.SMALL_CHAT_BAR
                            , CC.RED + "You do not have permission to use the  "
                                    + CC.WHITE + tag.getName() + CC.RED + " tag."
                            , CC.SMALL_CHAT_BAR
                    ).build();
                }
                return new ItemBuilder(Material.NAME_TAG).setDisplayName(tag.getDisplayName()).setLore(
                        CC.SMALL_CHAT_BAR
                        , CC.GREEN + "Click to select the " + tag.getName() + " tag"
                        , CC.SMALL_CHAT_BAR
                ).build();
            }

            @Override
            public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
                Profile profile = Profile.getByPlayer(player);
                if (!player.hasPermission("violet.tags." + tag.getName())) {
                    player.sendMessage(CC.RED + "You do not have permission to use this tag.");
                    return;
                }
                profile.setTag(tag);
                player.sendMessage(CC.GREEN + "You have selected the " + tag.getName() + " tag");
            }
        }));
        return buttons;
    }
}
