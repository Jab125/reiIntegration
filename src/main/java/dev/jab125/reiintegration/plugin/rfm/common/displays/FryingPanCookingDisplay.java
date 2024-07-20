package dev.jab125.reiintegration.plugin.rfm.common.displays;

import com.mrcrayfish.furniture.refurbished.crafting.ProcessingRecipe;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.registry.RecipeManagerContext;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Recipe;

import java.util.List;

public class FryingPanCookingDisplay extends BasicDisplay {
    public final ProcessingRecipe recipe;

    public FryingPanCookingDisplay(CampfireCookingRecipe recipe) {
        this(ProcessingRecipe.Item.from(recipe, BasicDisplay.registryAccess()));
    }

    public FryingPanCookingDisplay(ProcessingRecipe recipe) {
        this(EntryIngredients.ofIngredient(recipe.getIngredient()), recipe.getOutput(BasicDisplay.registryAccess()), recipe);
    }

    public FryingPanCookingDisplay(EntryIngredient input, ItemStack output, ProcessingRecipe recipe) {
        this(List.of(input), List.of(EntryIngredients.of(output)), recipe);
    }
    public FryingPanCookingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, ProcessingRecipe recipe) {
        super(inputs, outputs);
        this.recipe = recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return RfmPlugin.FRYING_PAN_COOKING;
    }

    public static DisplaySerializer<FryingPanCookingDisplay> serializer(Serializer.RecipeLessConstructor<FryingPanCookingDisplay> constructor) {
        return Serializer.of(constructor);
    }

    public FryingPanCookingDisplay(List<EntryIngredient> entryIngredients, List<EntryIngredient> entryIngredients1, NbtCompound nbtCompound) {
        this(entryIngredients, entryIngredients1, (ProcessingRecipe) RecipeManagerContext.getInstance().byId(nbtCompound, "location"));
    }
}
