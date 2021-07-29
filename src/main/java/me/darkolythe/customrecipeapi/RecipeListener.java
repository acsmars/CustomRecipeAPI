package me.darkolythe.customrecipeapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class RecipeListener implements Listener {

    Map<Player, BukkitTask> taskList = new HashMap<>();

    private CustomRecipeAPI plugin;
    RecipeListener(CustomRecipeAPI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void recipeCheck(InventoryOpenEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();

            BukkitTask id = new CraftItemTask(plugin, player, event.getInventory()).runTaskTimer(plugin, 1, 1);
            taskList.put(player, id);
        }
    }

    @EventHandler
    private void onCraftingClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            if (taskList.containsKey(player)) {
                Bukkit.getServer().getScheduler().cancelTask(taskList.get(player).getTaskId());
                taskList.remove(player);
            }
        }
    }
}
