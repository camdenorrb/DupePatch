package me.camdenorrb.droppatch;

import me.camdenorrb.droppatch.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by camdenorrb on 11/27/16.
 */
public class DropPatch extends JavaPlugin implements Listener {

    private boolean notify;
    private String notifyMsg;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        notify = getConfig().getBoolean("SendNotification", true);
        notifyMsg = ChatUtils.format(getConfig().getString("NotifyMsg", "&c&l$player, has been caught trying to Drop Glitch!"));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent event) {

        Player player = event.getPlayer();
        if (player.isOnline()) return;

        event.setCancelled(true);

        if (!notify) return;

        String replacedMsg = notifyMsg.replace("$player", player.getName());
        getServer().getOnlinePlayers().stream().filter(player1 -> player1.hasPermission("DropPatch.Notify")).forEach(player1 -> player1.sendMessage(replacedMsg));
    }
}
