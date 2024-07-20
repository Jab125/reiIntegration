package dev.jab125.reiintegration.plugin.rfm.common.displays;

import com.mrcrayfish.furniture.refurbished.crafting.OvenBakingRecipe;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.registry.RecipeManagerContext;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.List;

public class OvenBakingDisplay extends BasicDisplay {
    public final OvenBakingRecipe recipe;

    public OvenBakingDisplay(OvenBakingRecipe recipe) {
        this(EntryIngredients.ofIngredient(recipe.getIngredient()), recipe.getOutput(BasicDisplay.registryAccess()), recipe);
    }

    public OvenBakingDisplay(EntryIngredient input, ItemStack output, OvenBakingRecipe recipe) {
        this(List.of(input), List.of(EntryIngredients.of(output)), recipe);
    }
    public OvenBakingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, OvenBakingRecipe recipe) {
        super(inputs, outputs);
        this.recipe = recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return RfmPlugin.OVEN_BAKING;
    }

    public static DisplaySerializer<OvenBakingDisplay> serializer(Serializer.RecipeLessConstructor<OvenBakingDisplay> constructor) {
        return Serializer.of(constructor);
    }

    public OvenBakingDisplay(List<EntryIngredient> entryIngredients, List<EntryIngredient> entryIngredients1, NbtCompound nbtCompound) {
        this(entryIngredients, entryIngredients1, (OvenBakingRecipe) RecipeManagerContext.getInstance().byId(nbtCompound, "location"));
    }
}
