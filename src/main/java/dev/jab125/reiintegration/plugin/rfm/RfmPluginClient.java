package dev.jab125.reiintegration.plugin.rfm;

import com.mrcrayfish.furniture.refurbished.core.ModRecipeTypes;
import com.mrcrayfish.furniture.refurbished.crafting.*;
import dev.jab125.reiintegration.plugin.rfm.client.categories.*;
import dev.jab125.reiintegration.plugin.rfm.common.displays.*;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.RecipeType;

//#if LOADER <= FORGE
//$$ import me.shedaniel.rei.forge.REIPluginClient;
//$$ @REIPluginClient
//#endif
public class RfmPluginClient implements REIClientPlugin {
    //com.mrcrayfish.furniture.refurbished.compat.jei.Plugin
    //me.shedaniel.

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CuttingBoardCombiningCategory());
        //registry.add(new OldCuttingBoardCombiningCategory());
        registry.add(new CuttingBoardSlicingCategory());
        registry.add(new FreezerSolidifyingCategory());
        registry.add(new FryingPanCookingCategory());
        registry.add(new GrillCookingCategory());
        registry.add(new MicrowaveHeatingCategory());
        registry.add(new OvenBakingCategory());
        registry.add(new ToasterHeatingCategory());
        registry.add(new WorkbenchConstructingCategory());
        //REIClientPlugin.super.registerCategories(registry);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CuttingBoardCombiningRecipe.class, ModRecipeTypes.CUTTING_BOARD_COMBINING.get(), CuttingBoardCombiningDisplay::new);

        registry.registerRecipeFiller(CuttingBoardSlicingRecipe.class, ModRecipeTypes.CUTTING_BOARD_SLICING.get(), CuttingBoardSlicingDisplay::new);

        registry.registerRecipeFiller(FreezerSolidifyingRecipe.class, ModRecipeTypes.FREEZER_SOLIDIFYING.get(), FreezerSolidifyingDisplay::new);

        registry.registerRecipeFiller(FryingPanCookingRecipe.class, ModRecipeTypes.FRYING_PAN_COOKING.get(), FryingPanCookingDisplay::new);
        registry.registerRecipeFiller(CampfireCookingRecipe.class, RecipeType.CAMPFIRE_COOKING, FryingPanCookingDisplay::new);

        registry.registerRecipeFiller(GrillCookingRecipe.class, ModRecipeTypes.GRILL_COOKING.get(), GrillCookingDisplay::new);
        registry.registerRecipeFiller(CampfireCookingRecipe.class, RecipeType.CAMPFIRE_COOKING, GrillCookingDisplay::new);

        registry.registerRecipeFiller(MicrowaveHeatingRecipe.class, ModRecipeTypes.MICROWAVE_HEATING.get(), MicrowaveHeatingDisplay::new);

        registry.registerRecipeFiller(OvenBakingRecipe.class, ModRecipeTypes.OVEN_BAKING.get(), OvenBakingDisplay::new);

        registry.registerRecipeFiller(ToasterHeatingRecipe.class, ModRecipeTypes.TOASTER_HEATING.get(), ToasterHeatingDisplay::new);

        registry.registerRecipeFiller(WorkbenchContructingRecipe.class, ModRecipeTypes.WORKBENCH_CONSTRUCTING.get(), WorkbenchConstructingDisplay::new);
        //REIClientPlugin.super.registerDisplays(registry);
    }

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        REIClientPlugin.super.registerDisplaySerializer(registry);
    }
}
