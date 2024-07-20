package dev.jab125.reiintegration.plugin.rfm;

import dev.jab125.reiintegration.plugin.rfm.common.displays.*;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;

//#if LOADER <= FORGE
//$$ import me.shedaniel.rei.forge.REIPluginCommon;
//$$ @REIPluginCommon
//#endif
public class RfmPluginServer implements REIServerPlugin {
    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(RfmPlugin.CUTTING_BOARD_COMBINING, CuttingBoardCombiningDisplay.serializer(CuttingBoardCombiningDisplay::new));
        registry.register(RfmPlugin.CUTTING_BOARD_SLICING, CuttingBoardSlicingDisplay.serializer(CuttingBoardSlicingDisplay::new));
        registry.register(RfmPlugin.FREEZER_SOLIDIFYING, FreezerSolidifyingDisplay.serializer(FreezerSolidifyingDisplay::new));
        registry.register(RfmPlugin.FRYING_PAN_COOKING, FryingPanCookingDisplay.serializer(FryingPanCookingDisplay::new));
        registry.register(RfmPlugin.GRILL_COOKING, GrillCookingDisplay.serializer(GrillCookingDisplay::new));
        registry.register(RfmPlugin.MICROWAVE_HEATING, MicrowaveHeatingDisplay.serializer(MicrowaveHeatingDisplay::new));
        registry.register(RfmPlugin.OVEN_BAKING, OvenBakingDisplay.serializer(OvenBakingDisplay::new));
        registry.register(RfmPlugin.TOASTER_HEATING, ToasterHeatingDisplay.serializer(ToasterHeatingDisplay::new));
        registry.register(RfmPlugin.WORKBENCH_CONSTRUCTING, WorkbenchConstructingDisplay.serializer(WorkbenchConstructingDisplay::new));
    }
}
