package me.violet.tags.utils;

import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CC {
    public static final String BLUE = ChatColor.BLUE.toString();
    public static final String AQUA = ChatColor.AQUA.toString();
    public static final String YELLOW = ChatColor.YELLOW.toString();
    public static final String RED = ChatColor.RED.toString();
    public static final String GRAY = ChatColor.GRAY.toString();
    public static final String GOLD = ChatColor.GOLD.toString();
    public static final String GREEN = ChatColor.GREEN.toString();
    public static final String WHITE = ChatColor.WHITE.toString();
    public static final String BLACK = ChatColor.BLACK.toString();
    public static final String BOLD = ChatColor.BOLD.toString();
    public static final String ITALIC = ChatColor.ITALIC.toString();
    public static final String UNDER_LINE = ChatColor.UNDERLINE.toString();
    public static final String STRIKE_THROUGH = ChatColor.STRIKETHROUGH.toString();
    public static final String RESET = ChatColor.RESET.toString();
    public static final String MAGIC = ChatColor.MAGIC.toString();
    public static final String DBLUE = ChatColor.DARK_BLUE.toString();
    public static final String DAQUA = ChatColor.DARK_AQUA.toString();
    public static final String DGRAY = ChatColor.DARK_GRAY.toString();
    public static final String DGREEN = ChatColor.DARK_GREEN.toString();
    public static final String DPURPLE = ChatColor.DARK_PURPLE.toString();
    public static final String DRED = ChatColor.DARK_RED.toString();
    public static final String PURPLE = ChatColor.DARK_PURPLE.toString();
    public static final String PINK = ChatColor.LIGHT_PURPLE.toString();
    public static final String MENU_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "------------------------";
    public static final String CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "------------------------------------------------";
    public static final String SMALL_CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "-----------------";
    public static final String SB_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "----------------------";
    public static final String VERTICAL_BAR = ChatColor.GRAY.toString() + StringEscapeUtils.unescapeJava("\u2503");

    public static String translate(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static List<String> translate(List<String> lines) {
        List<String> toReturn = new ArrayList();
        for (String line : lines) {
            toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        return toReturn;
    }

    public static List<String> translate(String[] lines) {
        List<String> toReturn = new ArrayList();
        for (String line : lines) {
            if (line != null) {
                toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
            }
        }
        return toReturn;
    }

    public static String strip(String in) {
        return ChatColor.stripColor(in);
    }

    public static List<String> strip(List<String> lines) {
        List<String> toReturn = new ArrayList();
        for (String line : lines) {
            toReturn.add(ChatColor.stripColor(line));
        }
        return toReturn;
    }

    public static List<String> strip(String[] lines) {
        List<String> toReturn = new ArrayList();
        for (String line : lines) {
            if (line != null) {
                toReturn.add(ChatColor.stripColor(line));
            }
        }
        return toReturn;
    }

    public static String createUsage(String commandLabel, String arguments) {
        return ChatColor.RED.toString() + "Usage: " + ChatColor.GRAY.toString() + "/" + commandLabel + ChatColor.AQUA.toString() + " " + arguments;
    }

    public static String createUsage(String commandLabel) {
        return ChatColor.RED.toString() + "Usage: " + ChatColor.GRAY.toString() + "/" + commandLabel;
    }


    public static List<String> translatePlaceholders(List<String> list, Player player) {
        List<String> translated = new ArrayList<>();
        for (String s : list) {
            translated.add(translate(s.replace("%player%", player.getName())));
        }
        return translated;
    }

    public static String translatePlaceholders(String message, Player player) {
        return message.replace("%player%", player.getName());
    }


    public static class Replacement {
        public void setReplacements(Map<Object, Object> replacements) {
            this.replacements = replacements;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        private Map<Object, Object> replacements = new HashMap<>();

        private String message;

        public Map<Object, Object> getReplacements() {
            return this.replacements;
        }

        public String getMessage() {
            return this.message;
        }

        public Replacement(String message) {
            this.message = message;
        }

        public Replacement add(Object current, Object replacement) {
            this.replacements.put(current, replacement);
            return this;
        }

        public String toString() {
            this.replacements.keySet().forEach(current -> this.message = this.message.replace(String.valueOf(current), String.valueOf(this.replacements.get(current))));
            return translate(this.message);
        }

        public String toString(boolean ignored) {
            this.replacements.keySet().forEach(current -> this.message = this.message.replace(String.valueOf(current), String.valueOf(this.replacements.get(current))));
            return this.message;
        }
    }
}
