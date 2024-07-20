package dev.jab125.reiintegration.plugin.rfm.common.displays;

import com.mrcrayfish.furniture.refurbished.crafting.CuttingBoardCombiningRecipe;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.registry.RecipeManagerContext;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CuttingBoardCombiningDisplay extends BasicDisplay /*implements SimpleGridMenuDisplay*/ {

    private CuttingBoardCombiningRecipe recipe;

    public CuttingBoardCombiningDisplay(CuttingBoardCombiningRecipe recipe) {
        this(EntryIngredients.ofIngredients(recipe.getIngredients()), Collections.singletonList(EntryIngredients.of(recipe.getOutput(BasicDisplay.registryAccess()))),
                recipe, Optional.of(recipe.getId()));
        this.recipe = recipe;
    }

    //#if MC >= 1.20.4
    //$$ public CuttingBoardCombiningDisplay(net.minecraft.recipe.RecipeEntry<CuttingBoardCombiningRecipe> recipe) {
    //$$     this(recipe.value(), recipe.id());
    //$$ }
    //#endif

//    public CuttingBoardCombiningDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
//        super(inputs, outputs);
//    }

    public CuttingBoardCombiningDisplay(List<EntryIngredient> entryIngredients, List<EntryIngredient> outputs, CuttingBoardCombiningRecipe recipe, Optional<Identifier> id) {
        super(entryIngredients, outputs, id);
        this.recipe = recipe;
    }

    public CuttingBoardCombiningDisplay(List<EntryIngredient> entryIngredients, List<EntryIngredient> entryIngredients1, NbtCompound nbtCompound) {
        this(entryIngredients, entryIngredients1, (CuttingBoardCombiningRecipe) RecipeManagerContext.getInstance().byId(nbtCompound, "location"), Optional.ofNullable(RecipeManagerContext.getInstance().byId(nbtCompound, "location").getId()));
    }

//    public CuttingBoardCombiningDisplay(List<EntryIngredient> entryIngredients, List<EntryIngredient> entryIngredients1, Optional<Identifier> identifier) {
//        super();
//
//    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return RfmPlugin.CUTTING_BOARD_COMBINING;
    }

//    public static me.shedaniel.rei.api.common.display.basic.BasicDisplay.Serializer<CuttingBoardCombiningDisplay> serializer() {
//        return me.shedaniel.rei.api.common.display.basic.BasicDisplay.Serializer.ofSimple(CuttingBoardCombiningDisplay::new);
//    }

    public static DisplaySerializer<CuttingBoardCombiningDisplay> serializer(BasicDisplay.Serializer.RecipeLessConstructor<CuttingBoardCombiningDisplay> constructor) {
        return BasicDisplay.Serializer.ofRecipeLess(constructor, (display, tag) -> {
//            tag.putFloat("xp", display.getXp());
//            tag.putDouble("cookTime", display.getCookingTime());
        });
    }

//    @Override
//    public int getWidth() {
//        return 56;
//    }
//
//    @Override
//    public int getHeight() {
//        return 12;
//    }
}
