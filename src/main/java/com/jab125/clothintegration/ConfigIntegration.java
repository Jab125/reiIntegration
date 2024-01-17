package com.jab125.clothintegration;

//#if LOADER <= FORGE
//$$ import com.jab125.clothintegration.forge.ForgeIntegration;
//#endif
//#if HAS:FRAMEWORK
import com.jab125.clothintegration.framework.FrameworkIntegration;
//#endif
//#if HAS:JEI
import com.jab125.clothintegration.jei.JeiIntegration;
//#endif
import com.jab125.clothintegration.minecraft.MinecraftIntegration;
import com.jab125.clothintegration.platform.ConfigScreenUtil;
import com.jab125.clothintegration.platform.PlatformUtil;
import com.jab125.clothintegration.util.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

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
        ModConfig.a90();
        //#if HAS:JEI
        if (PlatformUtil.isModInstalled("jei") && !PlatformUtil.isModInstalled("rei_plugin_compatibilities")) new JeiIntegration();
        //#endif
        if (!PlatformUtil.getLoader().isFabricLike()) new MinecraftIntegration();
        //#if LOADER <= FORGE
        //$$ new ForgeIntegration();
        //#endif
        //#if HAS:FRAMEWORK
        if (PlatformUtil.isModInstalled("framework")) new FrameworkIntegration();
        //#endif
    }
}
