package com.jab125.clothintegration.platform;

//#if LOADER <= FORGE
import net.minecraftforge.fml.ModContainer;
//#elseif LOADER == FABRIC
//$$ import net.fabricmc.loader.api.ModContainer;
//#endif

public interface Mod {
    String getId();
    String getName();
    String getDescription();
    String getVersion();
    ModContainer backing();
}
