package dev.jab125.reiintegration.plugin.rfm.common.displays;

import com.mrcrayfish.furniture.refurbished.crafting.ToasterHeatingRecipe;
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

public class ToasterHeatingDisplay extends BasicDisplay {
    public final ToasterHeatingRecipe recipe;

    public ToasterHeatingDisplay(ToasterHeatingRecipe recipe) {
        this(EntryIngredients.ofIngredient(recipe.getIngredient()), recipe.getOutput(BasicDisplay.registryAccess()), recipe);
    }

    public ToasterHeatingDisplay(EntryIngredient input, ItemStack output, ToasterHeatingRecipe recipe) {
        this(List.of(input), List.of(EntryIngredients.of(output)), recipe);
    }
    public ToasterHeatingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, ToasterHeatingRecipe recipe) {
        super(inputs, outputs);
        this.recipe = recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return RfmPlugin.TOASTER_HEATING;
    }

    public static DisplaySerializer<ToasterHeatingDisplay> serializer(Serializer.RecipeLessConstructor<ToasterHeatingDisplay> constructor) {
        return Serializer.of(constructor);
    }

    public ToasterHeatingDisplay(List<EntryIngredient> entryIngredients, List<EntryIngredient> entryIngredients1, NbtCompound nbtCompound) {
        this(entryIngredients, entryIngredients1, (ToasterHeatingRecipe) RecipeManagerContext.getInstance().byId(nbtCompound, "location"));
    }
}
