package dev.jab125.reiintegration.plugin.rfm.client.categories;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrcrayfish.furniture.refurbished.compat.jei.Plugin;
import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import com.mrcrayfish.furniture.refurbished.util.Utils;
import dev.jab125.reiintegration.helper.DarkModeHelper;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import dev.jab125.reiintegration.plugin.rfm.client.widgets.OvenBakingArrowWidget;
import dev.jab125.reiintegration.plugin.rfm.client.widgets.WaveformWidget;
import dev.jab125.reiintegration.plugin.rfm.common.displays.MicrowaveHeatingDisplay;
import dev.jab125.reiintegration.plugin.rfm.common.displays.OvenBakingDisplay;
import me.shedaniel.clothconfig2.api.animator.NumberAnimator;
import me.shedaniel.clothconfig2.api.animator.ValueAnimator;
import me.shedaniel.math.Dimension;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.text.DecimalFormat;
import java.util.List;

public class OvenBakingCategory implements DisplayCategory<OvenBakingDisplay> {
    @Override
    public CategoryIdentifier<? extends OvenBakingDisplay> getCategoryIdentifier() {
        return RfmPlugin.OVEN_BAKING;
    }

    @Override
    public Text getTitle() {
        return Utils.translation("jei_category", "oven_baking");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.STOVE_LIGHT.get());
    }

    @Override
    public List<Widget> setupDisplay(OvenBakingDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.x + 4, bounds.y + 4 + 10);
        DecimalFormat df = new DecimalFormat("###.##");
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget(DarkModeHelper.rfmJei2(startPoint.x, startPoint.y, 177,  0, 79, 62)));
        final NumberAnimator<Float> darkBackgroundAlpha = ValueAnimator.ofFloat()
                .withConvention(() -> REIRuntime.getInstance().isDarkThemeEnabled() ? 1.0F : 0.0F, ValueAnimator.typicalTransitionTime())
                .asFloat();
        widgets.add(Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {
            darkBackgroundAlpha.update(delta);
            int offset = (int) (Util.getMeasuringTimeMs() / 100) % 3;
            int x = startPoint.x;
            int y = startPoint.y;
            graphics.drawTexture(RfmPlugin.TEXTURES_2, x + 5, y + 10, 120, offset * 40, 40, 40);
            if (darkBackgroundAlpha.value() > 0.0F) {
                RenderSystem.setShaderColor(1, 1, 1, darkBackgroundAlpha.value());
                graphics.drawTexture(RfmPlugin.TEXTURES_2_DARK, x + 5, y + 10, 120, offset * 40, 40, 40);
                RenderSystem.setShaderColor(1, 1, 1, 1);
            }
        }));
        widgets.add(Widgets.createLabel(new Point(bounds.x + bounds.width - 5, bounds.y + 5), Text.translatable("category.rei.campfire.time", df.format(display.recipe.getTime() / 20d))).noShadow().rightAligned().color(0xFF404040, 0xFFBBBBBB));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 58, startPoint.y + 5)).entries(display.getInputEntries().get(0)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 58, startPoint.y + 41)).entries(display.getOutputEntries().get(0)).markOutput().disableBackground());
        widgets.add(new OvenBakingArrowWidget(new Rectangle(new Point(startPoint.x + 57, startPoint.y + 24), new Dimension(17, 16))).animationDurationTicks(display.recipe.getTime()));
        return widgets;
    }

    @Override
    public int getDisplayWidth(OvenBakingDisplay display) {
        return 79 + 8;
    }

    @Override
    public int getDisplayHeight() {
        return 62 + 8 + 10;
    }
}
