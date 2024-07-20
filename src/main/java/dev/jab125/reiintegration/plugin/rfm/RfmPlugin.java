package dev.jab125.reiintegration.plugin.rfm;

import com.mrcrayfish.furniture.refurbished.util.Utils;
import dev.jab125.reiintegration.ReiIntegration;
import dev.jab125.reiintegration.plugin.rfm.common.displays.*;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import net.minecraft.util.Identifier;

public class RfmPlugin {
    // Thank you class loading
    public static final Identifier TEXTURES = Utils.resource("textures/gui/jei.png");
    public static final Identifier TEXTURES_DARK = ReiIntegration.id("rfm", "textures/gui/jei_dark.png");
    public static final Identifier TEXTURES_2 = Utils.resource("textures/gui/jei2.png");
    public static final Identifier TEXTURES_2_DARK = ReiIntegration.id("rfm", "textures/gui/jei2_dark.png");

    public static final CategoryIdentifier<CuttingBoardCombiningDisplay> CUTTING_BOARD_COMBINING = CategoryIdentifier.of(ReiIntegration.id("rfm", "cutting_board_combining"));
    public static final CategoryIdentifier<CuttingBoardSlicingDisplay> CUTTING_BOARD_SLICING = CategoryIdentifier.of(ReiIntegration.id("rfm", "cutting_board_slicing"));
    public static final CategoryIdentifier<FreezerSolidifyingDisplay> FREEZER_SOLIDIFYING = CategoryIdentifier.of(ReiIntegration.id("rfm", "freezer_solidifying"));
    public static final CategoryIdentifier<FryingPanCookingDisplay> FRYING_PAN_COOKING = CategoryIdentifier.of(ReiIntegration.id("rfm", "frying_pan_cooking"));
    public static final CategoryIdentifier<GrillCookingDisplay> GRILL_COOKING = CategoryIdentifier.of(ReiIntegration.id("rfm", "grill_cooking"));
    public static final CategoryIdentifier<MicrowaveHeatingDisplay> MICROWAVE_HEATING = CategoryIdentifier.of(ReiIntegration.id("rfm", "microwave_heating"));
    public static final CategoryIdentifier<OvenBakingDisplay> OVEN_BAKING = CategoryIdentifier.of(ReiIntegration.id("rfm", "oven_baking"));
    public static final CategoryIdentifier<ToasterHeatingDisplay> TOASTER_HEATING = CategoryIdentifier.of(ReiIntegration.id("rfm", "toaster_toasting"));
    public static final CategoryIdentifier<WorkbenchConstructingDisplay> WORKBENCH_CONSTRUCTING = CategoryIdentifier.of(ReiIntegration.id("rfm", "workbench_constructing"));
//    public static List<ProcessingRecipe> getFryingPanRecipes() {
//        List<ProcessingRecipe> recipes = new ArrayList();
//        recipes.addAll(this.getRecipes((RecipeType) ModRecipeTypes.FRYING_PAN_COOKING.get()));
//        recipes.addAll(this.getRecipes(RecipeType.CAMPFIRE_COOKING).stream().map((recipe) -> {
//            return ProcessingRecipe.Item.from(recipe, getRegistryAccess());
//        }).toList());
//        return recipes;
//    }
//
//    private List<ProcessingRecipe> getGrillRecipes() {
//        List<ProcessingRecipe> recipes = new ArrayList();
//        recipes.addAll(this.getRecipes((RecipeType)ModRecipeTypes.GRILL_COOKING.get()));
//        recipes.addAll(this.getRecipes(RecipeType.CAMPFIRE_COOKING).stream().map((recipe) -> {
//            return ProcessingRecipe.Item.from(recipe, getRegistryAccess());
//        }).toList());
//        return recipes;
//    }
}
