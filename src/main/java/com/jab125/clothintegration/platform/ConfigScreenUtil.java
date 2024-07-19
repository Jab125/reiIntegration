package com.jab125.clothintegration.platform;

import net.minecraft.client.gui.screen.Screen;
//#if LOADER <= FORGE
//#if LOADER == NEO && MC >= 1.20.5
//$$ import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//#else
import net.minecraftforge.client.ConfigScreenHandler;
//#endif
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import java.util.Optional;
//#endif

//#if LOADER >= FABRIC
//$$ import java.util.HashMap;
//#endif

import java.util.function.Function;

public class ConfigScreenUtil {
    //#if LOADER>=FABRIC
    //$$ public static final HashMap<String, Function<Screen, Screen>> configScreenMap = new HashMap<>();
    //#endif
    public static void addConfigScreenToMod(String id, Function<Screen, Screen> screenFunction) {
        //#if LOADER <= FORGE
        Optional<? extends ModContainer> modContainerById = ModList.get().getModContainerById(id);
        if (modContainerById.isPresent()) {
                 //#if MC < 1.20.5 || LOADER == FORGE
                 if(modContainerById.get().getCustomExtension(ConfigScreenHandler.ConfigScreenFactory.class).isPresent()) return;
                     modContainerById.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> screenFunction.apply(screen)));
                 //#else
                 //$$ if(modContainerById.get().getCustomExtension(IConfigScreenFactory.class).isPresent()) return;
                 //$$     modContainerById.get().registerExtensionPoint(IConfigScreenFactory.class, (mc, screen) -> screenFunction.apply(screen));
                 //#endif
        }
        //#else
        //$$ configScreenMap.putIfAbsent(id, screenFunction);
        //#endif
    }
}
