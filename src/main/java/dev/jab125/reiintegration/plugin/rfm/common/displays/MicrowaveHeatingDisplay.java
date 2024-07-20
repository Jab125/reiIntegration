package dev.jab125.reiintegration.plugin.rfm.common.displays;

import com.mrcrayfish.furniture.refurbished.crafting.MicrowaveHeatingRecipe;
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

public class MicrowaveHeatingDisplay extends BasicDisplay {
    public final MicrowaveHeatingRecipe recipe;

    public MicrowaveHeatingDisplay(MicrowaveHeatingRecipe recipe) {
        this(EntryIngredients.ofIngredient(recipe.getIngredient()), recipe.getOutput(BasicDisplay.registryAccess()), recipe);
    }

    public MicrowaveHeatingDisplay(EntryIngredient input, ItemStack output, MicrowaveHeatingRecipe recipe) {
        this(List.of(input), List.of(EntryIngredients.of(output)), recipe);
    }
    public MicrowaveHeatingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, MicrowaveHeatingRecipe recipe) {
        super(inputs, outputs);
        this.recipe = recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return RfmPlugin.MICROWAVE_HEATING;
    }

    public static DisplaySerializer<MicrowaveHeatingDisplay> serializer(Serializer.RecipeLessConstructor<MicrowaveHeatingDisplay> constructor) {
        return Serializer.of(constructor);
    }

    public MicrowaveHeatingDisplay(List<EntryIngredient> entryIngredients, List<EntryIngredient> entryIngredients1, NbtCompound nbtCompound) {
        this(entryIngredients, entryIngredients1, (MicrowaveHeatingRecipe) RecipeManagerContext.getInstance().byId(nbtCompound, "location"));
    }
}
