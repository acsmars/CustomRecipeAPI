package me.darkolythe.customrecipeapi;

import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;

public class APIManager {

    private final CustomRecipeAPI plugin;

    APIManager(CustomRecipeAPI plugin) {
        this.plugin = plugin;
    }

    private final List<CustomRecipe> recipes = new ArrayList<>();
    private ShapedRecipe workbench;

    /**
     * Add a recipe to the custom recipe list.
     * @param newRecipe CustomRecipe object to add
     */
    void addRecipe(CustomRecipe newRecipe) {
        recipes.add(newRecipe);
    }

    /**
     * Remove a recipe from the custom recipe list.
     * @param newRecipe CustomRecipe object to remove
     */
    public void removeRecipe(CustomRecipe newRecipe) {
        recipes.remove(newRecipe);
    }

    public List<CustomRecipe> getRecipes() {
        return recipes;
    }

    /**
     * Sets the recipe and item for the workbench with the recipe.
     * @param newRecipe ShapedRecipe for workbench
     */
    public void setWorkBench(ShapedRecipe newRecipe) {
        workbench = newRecipe;
    }

    public ShapedRecipe getWorkbench() {
        return workbench;
    }
}
