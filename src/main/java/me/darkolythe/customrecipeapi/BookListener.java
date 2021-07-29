package me.darkolythe.customrecipeapi;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;


public class BookListener implements Listener {

    private CustomRecipeAPI plugin;

    BookListener(CustomRecipeAPI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onBookClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null) {
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                if (event.getView().getTitle().equals(LanguageManager.getValue("recipebook"))) {
                    event.setCancelled(true);
                    ItemStack item = event.getCurrentItem();
                    if (item != null && item.getItemMeta() != null) {
                        if (item.getItemMeta().getDisplayName().equals(LanguageManager.getValue("nextpage")) && item.getType() == Material.ARROW) {
                            int page = Integer.parseInt(item.getItemMeta().getLore().get(0)
                                    .replace(ChatColor.GREEN.toString(), "")
                                    .replace(ChatColor.GRAY.toString(), "")
                                    .replaceAll("[^\\d]", ""));
                            player.openInventory(plugin.getBookManager().getRecipeBook(player, page + 1));
                        } else if (item.getItemMeta().getDisplayName().equals(LanguageManager.getValue("previouspage")) && item.getType() == Material.ARROW) {
                            int page = Integer.parseInt(item.getItemMeta().getLore().get(0)
                                    .replace(ChatColor.GREEN.toString(), "")
                                    .replace(ChatColor.GRAY.toString(), "")
                                    .replaceAll("[^\\d]", ""));
                            player.openInventory(plugin.getBookManager().getRecipeBook(player, page - 1));
                        } else {
                            if (!event.getClickedInventory().equals(player.getInventory())) {
                                if (event.getClick() == ClickType.RIGHT && player.hasPermission("crapi.giveitem")) {
                                    player.getInventory().addItem(plugin.getBookManager().getRecipeFromItem(item).getResult());
                                } else {
                                    plugin.getBookManager().openRecipe(player, item);
                                }
                            }
                        }
                    }
                } else if (event.getView().getTitle().equals(LanguageManager.getValue("customrecipeview"))) {
                    event.setCancelled(true);
                    ItemStack item = event.getCurrentItem();
                    if (item != null && item.equals(plugin.getBookManager().createBackButton())) {
                        player.openInventory(plugin.getBookManager().getRecipeBook(player, 0));
                    } else if (item != null && item.equals(plugin.getBookManager().createDeleteButton())) {
                        CustomRecipe recipe = plugin.getRecipecreator().createRecipeFromInventory(event.getClickedInventory());
                        for (CustomRecipe r : plugin.getAPIManager().getRecipes()) {
                            if (r.equals(recipe)) {
                                plugin.getAPIManager().removeRecipe(r);
                                break;
                            }
                        }
                        plugin.getConfigHandler().saveRecipes();
                        player.openInventory(plugin.getBookManager().getRecipeBook(player, 0));
                    }
                }
            }
        }
    }
}
