//#if HAS:JEI
package com.jab125.clothintegration.jei;

import com.jab125.clothintegration.Integration;
import com.jab125.clothintegration.platform.ConfigScreenUtil;
import net.minecraft.util.Identifier;

public class JeiIntegration extends Integration {

    @Override
    protected void init() {
        setupIntegration();
    }

    private void setupIntegration() {
        JeiCloth jeiCloth = new JeiCloth();
        ConfigScreenUtil.addConfigScreenToMod("jei", jeiCloth::createScreen);
    }

    @Override
    protected Identifier getId() {
        return com.jab125.clothintegration.ConfigIntegration.id("roughlyenoughconfigscreens:jei");
    }
}
//#endif