package me.violet.tags.utils.menu.page;


import me.violet.tags.utils.ItemBuilder;
import me.violet.tags.utils.menu.Button;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;


public class PageButton extends Button {

    private int mod;
    private PagedMenu menu;

    public PageButton(int mod, PagedMenu menu) {
        this.mod = mod;
        this.menu = menu;
    }


    @Override
    public ItemStack getItem(Player player) {
        if (this.hasNext(player)) {
            return new ItemBuilder(Material.ARROW)
                    .setDisplayName(mod > 0 ? ChatColor.GOLD + "Next Page" : ChatColor.GOLD + "Previous Page")
                    .build();
        } else {
            return new ItemBuilder(Material.ARROW)
                    .setDisplayName(mod > 0 ? ChatColor.GOLD + "Last Page" : ChatColor.GOLD + "First Page")
                    .build();
        }
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (hasNext(player)) {
            this.menu.modPage(player, mod);
        }
    }

    private boolean hasNext(Player player) {
        int pg = this.menu.getPage() + this.mod;
        return pg > 0 && this.menu.getPages(player) >= pg;
    }

}
