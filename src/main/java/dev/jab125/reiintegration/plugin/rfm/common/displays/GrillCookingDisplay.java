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

import java.util.List;

public class GrillCookingDisplay extends BasicDisplay {
    public final ProcessingRecipe recipe;

    public GrillCookingDisplay(CampfireCookingRecipe recipe) {
        this(ProcessingRecipe.Item.from(recipe, BasicDisplay.registryAccess()));
    }

    public GrillCookingDisplay(ProcessingRecipe recipe) {
        this(EntryIngredients.ofIngredient(recipe.getIngredient()), recipe.getOutput(BasicDisplay.registryAccess()), recipe);
    }

    public GrillCookingDisplay(EntryIngredient input, ItemStack output, ProcessingRecipe recipe) {
        this(List.of(input), List.of(EntryIngredients.of(output)), recipe);
    }
    public GrillCookingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, ProcessingRecipe recipe) {
        super(inputs, outputs);
        this.recipe = recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return RfmPlugin.GRILL_COOKING;
    }

    public static DisplaySerializer<GrillCookingDisplay> serializer(Serializer.RecipeLessConstructor<GrillCookingDisplay> constructor) {
        return Serializer.of(constructor);
    }

    public GrillCookingDisplay(List<EntryIngredient> entryIngredients, List<EntryIngredient> entryIngredients1, NbtCompound nbtCompound) {
        this(entryIngredients, entryIngredients1, (ProcessingRecipe) RecipeManagerContext.getInstance().byId(nbtCompound, "location"));
    }
}
