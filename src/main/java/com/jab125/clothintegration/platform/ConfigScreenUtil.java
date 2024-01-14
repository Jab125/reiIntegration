package com.jab125.clothintegration.platform;

import net.minecraft.client.gui.screen.Screen;
//#if LOADER <= FORGE
//$$ import net.minecraftforge.client.ConfigScreenHandler;
//$$ import net.minecraftforge.fml.ModContainer;
//$$ import net.minecraftforge.fml.ModList;
//$$ import java.util.Optional;
//#endif

//#if LOADER >= FABRIC
import java.util.HashMap;
//#endif

import java.util.function.Function;

public class ConfigScreenUtil {
    //#if LOADER>=FABRIC
    public static final HashMap<String, Function<Screen, Screen>> configScreenMap = new HashMap<>();
    //#endif
    public static void addConfigScreenToMod(String id, Function<Screen, Screen> screenFunction) {
        //#if LOADER <= FORGE
        //$$ Optional<? extends ModContainer> modContainerById = ModList.get().getModContainerById(id);
        //$$ if (modContainerById.isPresent()) {
        //$$     if(modContainerById.get().getCustomExtension(ConfigScreenHandler.ConfigScreenFactory.class).isPresent()) return;
        //$$     modContainerById.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> screenFunction.apply(screen)));
        //$$ }
        //#else
        configScreenMap.putIfAbsent(id, screenFunction);
        //#endif
    }
}
