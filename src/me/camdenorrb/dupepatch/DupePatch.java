package me.camdenorrb.dupepatch;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.of;
import static org.bukkit.Bukkit.getOnlinePlayers;

public class DupePatch extends JavaPlugin implements Listener {

    private static final String BASE_PATH = "Notification.";
    private static DupePatch instance;


    private boolean notifyEnabled;

    private String notifyPrefix;
    private String notifyMsg;
    private String notifyPerm;


    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        loadConfig();

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        instance = null;
        notifyPrefix = null;
        notifyMsg = null;
        notifyPerm = null;
    }


    private void loadConfig() {
        setNotifyEnabled(getConfig().getBoolean(BASE_PATH + "Enabled", true));
        setNotifyPrefix(getConfig().getString("Prefix", "&a&Dupe&e&lPatch &7| "));
        setNotifyMsg(getConfig().getString(BASE_PATH + "Message", "&c&l%player_name%, was caught trying to Drop Glitch!"));

        notifyPerm = getConfig().getString(BASE_PATH + "Permission", "dupePatch.notify");
    }


    public boolean isNotifyEnabled() {
        return notifyEnabled;
    }

    public void setNotifyEnabled(boolean notifyEnabled) {
        this.notifyEnabled = notifyEnabled;
    }

    public String getNotifyPrefix() {
        return notifyPrefix;
    }

    public void setNotifyPrefix(String notifyPrefix) {
        this.notifyPrefix = format(notifyPrefix);
    }

    public String getNotifyMsg() {
        return notifyMsg;
    }

    public void setNotifyMsg(String notifyMsg) {
        this.notifyMsg = format(notifyMsg);
    }

    public String getNotifyPerm() {
        return notifyPerm;
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (player.isOnline()) return;
        event.setCancelled(true);

        if (isNotifyEnabled()) {
            String notification = getNotifyPrefix().concat(getNotifyMsg().replace("%player_name%", player.getName()));
            receivers().forEach(it -> it.sendMessage(notification));
        }
    }


    public static DupePatch getInstance() {
        return instance;
    }

    private static String format(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    private static Stream<? extends CommandSender> receivers() {
        return concat(getOnlinePlayers().stream().filter(player -> player.hasPermission(getInstance().getNotifyPerm())), of(Bukkit.getConsoleSender()));
    }

}
