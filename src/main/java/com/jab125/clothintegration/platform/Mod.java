package com.jab125.clothintegration.platform;

public interface Mod {
    String getId();
    String getName();
    String getDescription();
    String getVersion();

    //#if LOADER == FABRIC
    net.fabricmc.loader.api.ModContainer backing();
    //#elseif LOADER <= FORGE
    //$$ // TODO
    //#endif
}
