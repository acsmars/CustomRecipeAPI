package me.darkolythe.customrecipeapi;

import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomRecipeAPI extends JavaPlugin {

    public static String prefix = ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "[" + ChatColor.BLUE.toString() + "CRAPI" + ChatColor.WHITE.toString() + ChatColor.BOLD.toString() + "] ";

    public CustomRecipeAPI plugin;

    private RecipeListener recipelistener;
    private BookListener booklistener;
    @Getter
    private RecipeCreator recipecreator;
    @Getter
    private APIManager apimanager;
    @Getter
    private WorkbenchManager workbenchmanager;
    private ConfigHandler confighandler;
    @Getter
    private BookManager bookManager;

    @Override
    public void onEnable() {
        plugin = this;

        LanguageManager.setup(this);

        ConfigurationSerialization.registerClass(CustomRecipe.class);

        apimanager = new APIManager(this);
        bookManager = new BookManager(this);

        recipelistener = new RecipeListener(this);
        booklistener = new BookListener(this);
        recipecreator = new RecipeCreator(this);
        confighandler = new ConfigHandler(this);
        workbenchmanager = new WorkbenchManager(this);

        confighandler.setup();
        getServer().getPluginManager().registerEvents(recipelistener, this);
        getServer().getPluginManager().registerEvents(booklistener, this);
        getServer().getPluginManager().registerEvents(recipecreator, this);
        getServer().getPluginManager().registerEvents(workbenchmanager, this);

        getCommand("crapi").setExecutor(new CommandHandler(this));

        saveDefaultConfig();

        confighandler.loadRecipes();
        confighandler.loadWorkbench();

        Metrics metrics = new Metrics(plugin);

        System.out.println(prefix + ChatColor.GREEN + "CustomRecipeAPI enabled!");
    }

    @Override
    public void onDisable() {
        confighandler.saveRecipes();
        confighandler.saveWorkbench();
    }

    public APIManager getAPIManager() {
        return apimanager;
    }

    public ConfigHandler getConfigHandler() {
        return confighandler;
    }

    /**
     * Create a new recipe with ItemStacks. The order is the same as the ShapedRecipe order. For blank spots, use AIR as an ItemStack.
     * The recipe must first be created, then added to the CustomRecipeAPI to be in use.
     * @param newResult the ItemStack to return when crafted.
     * @param newRecipe ItemStacks to use in recipe. max of 9. 10+ will be ignored.
     */
    public CustomRecipe createRecipe(ItemStack newResult, ItemStack... newRecipe) {
        CustomRecipe newCustomRecipe = new CustomRecipe(newResult, newRecipe);
        newCustomRecipe.setFromPlugin(true);

        apimanager.addRecipe(newCustomRecipe);
        return newCustomRecipe;
    }
}
