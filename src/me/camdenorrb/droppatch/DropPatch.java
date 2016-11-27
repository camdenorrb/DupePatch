package me.camdenorrb.droppatch;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by camdenorrb on 11/27/16.
 */
public class DropPatch extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onDrop(PlayerDropItemEvent event) { if (!event.getPlayer().isOnline()) event.setCancelled(true); }

}
