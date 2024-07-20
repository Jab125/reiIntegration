/*
 * This file is licensed under the MIT License, part of Roughly Enough Items.
 * Copyright (c) 2018, 2019, 2020, 2021, 2022, 2023 shedaniel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.jab125.reiintegration.plugin.rfm.client.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import me.shedaniel.clothconfig2.api.animator.NumberAnimator;
import me.shedaniel.clothconfig2.api.animator.ValueAnimator;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.gui.widgets.Arrow;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class OvenBakingArrowWidget extends Arrow {
    private final Rectangle bounds;
    private double animationDuration = -1;
    private NumberAnimator<Float> darkBackgroundAlpha = ValueAnimator.ofFloat()
            .withConvention(() -> REIRuntime.getInstance().isDarkThemeEnabled() ? 1.0F : 0.0F, ValueAnimator.typicalTransitionTime())
            .asFloat();

    public OvenBakingArrowWidget(Rectangle bounds) {
        this.bounds = new Rectangle(Objects.requireNonNull(bounds));
    }
    
    @Override
    public double getAnimationDuration() {
        return animationDuration;
    }
    
    @Override
    public void setAnimationDuration(double animationDurationMS) {
        this.animationDuration = animationDurationMS;
        if (this.animationDuration <= 0)
            this.animationDuration = -1;
    }
    
    @ApiStatus.Internal
    public void setDarkBackgroundAlpha(NumberAnimator<Float> darkBackgroundAlpha) {
        this.darkBackgroundAlpha = darkBackgroundAlpha;
    }
    
    @Override
    public Rectangle getBounds() {
        return bounds;
    }
    
    @Override
    public void render(DrawContext graphics, int mouseX, int mouseY, float delta) {
        this.darkBackgroundAlpha.update(delta);
        renderBackground(graphics, false, 1.0F);
        if (darkBackgroundAlpha.value() > 0.0F) {
            renderBackground(graphics, true, this.darkBackgroundAlpha.value());
        }
    }
    
    public void renderBackground(DrawContext graphics, boolean dark, float alpha) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(770, 771, 1, 0);
        RenderSystem.blendFunc(770, 771);
        Identifier texture = dark ? RfmPlugin.TEXTURES_2_DARK : RfmPlugin.TEXTURES_2;
        if (getAnimationDuration() > 0) {
            int height = MathHelper.ceil((System.currentTimeMillis() / (animationDuration / 16) % 16d));
            //graphics.drawTexture(texture, getX() + width, getY(), 93 + width, 154, 24 - width, 17);
            graphics.drawTexture(texture, getX(), getY(), 160, 0, 17, height);
        } else {
            graphics.drawTexture(texture, getX(), getY(), 160, 0, 17, 16);
        } // 93, 154, 24, 17
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }; // 160, 0, 17, 16
    
    @Override
    public List<? extends Element> children() {
        return Collections.emptyList();
    }
}
