package com.jab125.clothintegration;

import com.jab125.clothintegration.util.ModConfig;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public abstract class Integration {
    public Integration() {
        if (ModConfig.MOD_CONFIG.get().blacklistedIntegrations.stream().noneMatch(a -> this.getId().toString().equals(a))) {
            System.out.println("[ClothIntegration] Setup " + this.getId() + " compatibility");
            init();
        }
    }

    protected abstract void init();

    protected abstract Identifier getId();
}
