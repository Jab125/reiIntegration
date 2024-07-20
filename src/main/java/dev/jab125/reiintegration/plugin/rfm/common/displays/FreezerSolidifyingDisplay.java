package dev.jab125.reiintegration.plugin.rfm.common.displays;

import com.mrcrayfish.furniture.refurbished.crafting.CuttingBoardSlicingRecipe;
import com.mrcrayfish.furniture.refurbished.crafting.FreezerSolidifyingRecipe;
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

public class FreezerSolidifyingDisplay extends BasicDisplay {
    public final FreezerSolidifyingRecipe recipe;
    public FreezerSolidifyingDisplay(FreezerSolidifyingRecipe recipe) {
        this(EntryIngredients.ofIngredient(recipe.getIngredient()), recipe.getOutput(BasicDisplay.registryAccess()), recipe);
    }

    public FreezerSolidifyingDisplay(EntryIngredient input, ItemStack output, FreezerSolidifyingRecipe recipe) {
        this(List.of(input), List.of(EntryIngredients.of(output)), recipe);
    }
    public FreezerSolidifyingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, FreezerSolidifyingRecipe recipe) {
        super(inputs, outputs);
        this.recipe = recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return RfmPlugin.FREEZER_SOLIDIFYING;
    }

    public static DisplaySerializer<FreezerSolidifyingDisplay> serializer(Serializer.RecipeLessConstructor<FreezerSolidifyingDisplay> constructor) {
        return Serializer.of(constructor);
    }

    public FreezerSolidifyingDisplay(List<EntryIngredient> entryIngredients, List<EntryIngredient> entryIngredients1, NbtCompound nbtCompound) {
        this(entryIngredients, entryIngredients1, (FreezerSolidifyingRecipe) RecipeManagerContext.getInstance().byId(nbtCompound, "location"));
    }
}
