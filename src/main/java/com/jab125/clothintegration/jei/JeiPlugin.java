//#if HAS:JEI
package com.jab125.clothintegration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.runtime.IJeiRuntime;
import mezz.jei.api.runtime.config.IJeiConfigManager;
import net.minecraft.util.Identifier;

@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return new Identifier("jeicloth");
    }
    public static IJeiConfigManager manager;

    @Override
    public void onConfigManagerAvailable(IJeiConfigManager configManager) {
        manager = configManager;
    }
}
//#endif