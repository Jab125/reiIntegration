package dev.jab125.reiintegration.plugin.rfm.common.displays;

import com.mrcrayfish.furniture.refurbished.crafting.StackedIngredient;
import com.mrcrayfish.furniture.refurbished.crafting.ToasterHeatingRecipe;
import com.mrcrayfish.furniture.refurbished.crafting.WorkbenchContructingRecipe;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.registry.RecipeManagerContext;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.collection.DefaultedList;

import java.util.*;

public class WorkbenchConstructingDisplay extends BasicDisplay {
    public final WorkbenchContructingRecipe recipe;

    public WorkbenchConstructingDisplay(WorkbenchContructingRecipe recipe) {
        this(process(recipe.getMaterials()).stream().toList(), recipe.getOutput(BasicDisplay.registryAccess()), recipe);
    }

    private static Collection<EntryIngredient> process(DefaultedList<StackedIngredient> materials) {
        //r//ecipe.getMaterials().stream().map(a -> new ItemStack(a.ingredient().getMatchingStacks(), a.count())
        Set<EntryIngredient> ingredients = new HashSet<>();
        for (StackedIngredient material : materials) {
            Ingredient ingredient = material.ingredient();
            int count = material.count();
            List<ItemStack> f = new ArrayList<>();
            for (ItemStack matchingStack : ingredient.getMatchingStacks()) {
                f.add(new ItemStack(matchingStack.getItem(), count));
            }
            ingredients.add(EntryIngredients.ofItemStacks(f));
        }
        return ingredients;
    }

    public WorkbenchConstructingDisplay(List<EntryIngredient> inputs, ItemStack output, WorkbenchContructingRecipe recipe) {
        this(inputs, List.of(EntryIngredients.of(output)), recipe);
    }
    public WorkbenchConstructingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, WorkbenchContructingRecipe recipe) {
        super(inputs, outputs);
        this.recipe = recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return RfmPlugin.WORKBENCH_CONSTRUCTING;
    }

    public static DisplaySerializer<WorkbenchConstructingDisplay> serializer(Serializer.RecipeLessConstructor<WorkbenchConstructingDisplay> constructor) {
        return Serializer.of(constructor);
    }

    public WorkbenchConstructingDisplay(List<EntryIngredient> entryIngredients, List<EntryIngredient> entryIngredients1, NbtCompound nbtCompound) {
        this(entryIngredients, entryIngredients1, (WorkbenchContructingRecipe) RecipeManagerContext.getInstance().byId(nbtCompound, "location"));
    }
}
