package com.jab125.clothintegration.minecraft;

import com.jab125.clothintegration.Integration;
import com.jab125.clothintegration.platform.ConfigScreenUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.util.Identifier;

public class MinecraftIntegration extends Integration {
    public MinecraftIntegration() {
        super();
    }

    @Override
    protected void init() {
        ConfigScreenUtil.addConfigScreenToMod("minecraft", s -> new OptionsScreen(s, MinecraftClient.getInstance().options));
    }

    @Override
    protected Identifier getId() {
        return new Identifier("roughlyenoughconfigscreens:minecraft");
    }
}
