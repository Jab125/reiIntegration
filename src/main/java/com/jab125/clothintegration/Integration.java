package com.jab125.clothintegration;

import net.minecraft.util.Identifier;

public abstract class Integration {
    public Integration() {
        System.out.println("[ClothIntegration] Setup " + this.getId() + " compatibility");
    }

    protected abstract Identifier getId();
}
