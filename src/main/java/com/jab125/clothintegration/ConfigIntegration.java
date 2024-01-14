package com.jab125.clothintegration;

//#if LOADER <= FORGE
//$$ import com.jab125.clothintegration.forge.ForgeIntegration;
//#endif
import com.jab125.clothintegration.framework.FrameworkIntegration;
import com.jab125.clothintegration.jei.JeiIntegration;
import com.jab125.clothintegration.minecraft.MinecraftIntegration;
import com.jab125.clothintegration.platform.PlatformUtil;

public class ConfigIntegration {
    public static ConfigIntegration INSTANCE;

    public static void init() {
        if (INSTANCE != null) {
            System.out.println("[ConfigIntegration] Already Initialized!");
            return;
        }
        INSTANCE = new ConfigIntegration();
    }

    private ConfigIntegration() {
        if (PlatformUtil.isModInstalled("jei") && !PlatformUtil.isModInstalled("rei_plugin_compatibilities")) new JeiIntegration();
        if (!PlatformUtil.getLoader().isFabricLike()) new MinecraftIntegration();
        //#if LOADER <= FORGE
        //$$ new ForgeIntegration();
        //#endif
        if (PlatformUtil.isModInstalled("framework")) new FrameworkIntegration();
    }
}
