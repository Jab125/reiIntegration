package dev.jab125.reiintegration.plugin.rfm.client.categories;

import com.google.common.collect.Lists;
import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import com.mrcrayfish.furniture.refurbished.core.ModItems;
import com.mrcrayfish.furniture.refurbished.util.Utils;
import dev.jab125.reiintegration.helper.DarkModeHelper;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import dev.jab125.reiintegration.plugin.rfm.client.widgets.WaveformWidget;
import dev.jab125.reiintegration.plugin.rfm.common.displays.GrillCookingDisplay;
import dev.jab125.reiintegration.plugin.rfm.common.displays.MicrowaveHeatingDisplay;
import me.shedaniel.math.Dimension;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.Text;

import java.text.DecimalFormat;
import java.util.List;

public class MicrowaveHeatingCategory implements DisplayCategory<MicrowaveHeatingDisplay> {
    @Override
    public CategoryIdentifier<? extends MicrowaveHeatingDisplay> getCategoryIdentifier() {
        return RfmPlugin.MICROWAVE_HEATING;
    }

    @Override
    public Text getTitle() {
        return Utils.translation("jei_category", "microwave_heating");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.MICROWAVE_LIGHT.get());
    }

    @Override
    public List<Widget> setupDisplay(MicrowaveHeatingDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.x + 4, bounds.y + 4 + 10);
        DecimalFormat df = new DecimalFormat("###.##");
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget(DarkModeHelper.rfmJei1(startPoint.x, startPoint.y, 0,  154, 93, 36)));

        widgets.add(Widgets.createLabel(new Point(bounds.x + bounds.width - 5, bounds.y + 5), Text.translatable("category.rei.campfire.time", df.format(display.recipe.getTime() / 20d))).noShadow().rightAligned().color(0xFF404040, 0xFFBBBBBB));

        //        builder.addSlot(RecipeIngredientRole.INPUT, 7, 10).addIngredients(recipe.getIngredients().get(0));
//        builder.addSlot(RecipeIngredientRole.OUTPUT, 67, 10).addItemStack(Plugin.getResult(recipe));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 7, startPoint.y + 10)).entries(display.getInputEntries().get(0)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 67, startPoint.y + 10)).entries(display.getOutputEntries().get(0)).markOutput().disableBackground());
        widgets.add(new WaveformWidget(new Rectangle(new Point(startPoint.x + 30, startPoint.y + 9), new Dimension(24, 17))).animationDurationTicks(display.recipe.getTime()));
        return widgets;
    }

    @Override
    public int getDisplayWidth(MicrowaveHeatingDisplay display) {
        return 93 + 8;
    }

    @Override
    public int getDisplayHeight() {
        return 36 + 8 + 10;
    }
}
