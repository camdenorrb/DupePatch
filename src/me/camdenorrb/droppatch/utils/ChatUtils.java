package me.camdenorrb.droppatch.utils;

import org.bukkit.ChatColor;

/**
 * Created by camdenorrb on 11/27/16.
 */
public class ChatUtils {

    public static String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
