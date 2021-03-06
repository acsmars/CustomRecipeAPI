# CustomRecipeAPI
An API to make creating custom recipes in a custom table easier. Workbench limits the user to Materials.

Custom Recipe API (CRAPI)
Custom Recipe API (CRAPI) is an API that makes creating custom recipes super easy. This API does require some programming knowledge, but you can start creating custom recipes within 5 minutes of downloading if you know what you're doing.

WORKS GREAT WITH: ItemCreatorPlus

How to get started
1. Download CRAPI
2. Add it as a dependency to your plugin. / As a library
3. Start making custom recipes!

NOTE: Custom Recipes can now be made in-game with the command /crapi new, in a super easy-to-use GUI


What you need to know to create recipes in your plugin
First you need to initialize the craft bench. You NEED to choose a Dispenser for the crafting bench, but the recipe is up to you.
Here's an example of what I did for one of my other plugins:
[code]ItemStack workbenchItem = new ItemStack(Material.DISPENSER);
ItemMeta meta = workbenchItem.getItemMeta();
meta.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + "Special Crafting");
workbenchItem.setItemMeta(meta);
ShapedRecipe workbenchRecipe = new ShapedRecipe(new NamespacedKey(main, "Workbench"), workbenchItem);
workbenchRecipe.shape("IGI", "GDG", "IGI");
workbenchRecipe.setIngredient('I', Material.IRON_BLOCK);
workbenchRecipe.setIngredient('G', Material.GLASS);
workbenchRecipe.setIngredient('D', Material.DIAMOND_BLOCK);
Bukkit.getServer().addRecipe(workbenchRecipe);

main.crapimanager.setWorkBench(workbenchRecipe);[/code]

You can make a custom itemstack for the Dispenser and a custom recipe using regular materials (since this has to be crafted in the crafting table).

Next up, you'll want to get started on some recipes. Here's an example of what I did.

[code]ItemStack redstone = new ItemStack(Material.REDSTONE);
ItemStack quartz = new ItemStack(Material.QUARTZ);
ItemStack gold = new ItemStack(Material.GOLD_INGOT);

ItemStack storageCell = new ItemStack(Material.STONE_AXE);

CustomRecipeAPI.createRecipe(storageCell, redstone, quartz, redstone, quartz, gold, quartz, redstone, quartz, redstone);[/code]

The order of the items goes like this:
[1, 2, 3
4, 5, 6   ->  0
7, 8, 9]

You can either put air, or nothing (nothing only works when there are no more items after it) to leave the slots empty.



For the JavaDocs here's a download link: https://tinyurl.com/yeo6wo63

For any help, feel free to contact me on my discord.
https://discord.gg/AET9mWj
