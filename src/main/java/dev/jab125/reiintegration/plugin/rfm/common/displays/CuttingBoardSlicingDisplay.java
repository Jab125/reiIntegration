package dev.jab125.reiintegration.plugin.rfm.common.displays;

import com.mrcrayfish.furniture.refurbished.crafting.CuttingBoardCombiningRecipe;
import com.mrcrayfish.furniture.refurbished.crafting.CuttingBoardSlicingRecipe;
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

public class CuttingBoardSlicingDisplay extends BasicDisplay {
    public final CuttingBoardSlicingRecipe recipe;
    public CuttingBoardSlicingDisplay(CuttingBoardSlicingRecipe recipe) {
        this(EntryIngredients.ofIngredient(recipe.getIngredient()), recipe.getOutput(BasicDisplay.registryAccess()), recipe);
    }

    public CuttingBoardSlicingDisplay(EntryIngredient input, ItemStack output, CuttingBoardSlicingRecipe recipe) {
        this(List.of(input), List.of(EntryIngredients.of(output)), recipe);
    }
    public CuttingBoardSlicingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, CuttingBoardSlicingRecipe recipe) {
        super(inputs, outputs);
        this.recipe = recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return RfmPlugin.CUTTING_BOARD_SLICING;
    }

    public static DisplaySerializer<CuttingBoardSlicingDisplay> serializer(BasicDisplay.Serializer.RecipeLessConstructor<CuttingBoardSlicingDisplay> constructor) {
        return BasicDisplay.Serializer.ofRecipeLess(constructor, (display, tag) -> {
//            tag.putFloat("xp", display.getXp());
//            tag.putDouble("cookTime", display.getCookingTime());
        });
    }

    public CuttingBoardSlicingDisplay(List<EntryIngredient> entryIngredients, List<EntryIngredient> entryIngredients1, NbtCompound nbtCompound) {
        this(entryIngredients, entryIngredients1, (CuttingBoardSlicingRecipe) RecipeManagerContext.getInstance().byId(nbtCompound, "location"));
    }
}
