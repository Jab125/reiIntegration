package dev.jab125.reiintegration.plugin.rfm.client.categories;

import com.google.common.collect.Lists;
import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import com.mrcrayfish.furniture.refurbished.core.ModItems;
import com.mrcrayfish.furniture.refurbished.util.Utils;
import dev.jab125.reiintegration.helper.DarkModeHelper;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import dev.jab125.reiintegration.plugin.rfm.common.displays.GrillCookingDisplay;
import dev.jab125.reiintegration.plugin.rfm.common.displays.ToasterHeatingDisplay;
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

public class ToasterHeatingCategory implements DisplayCategory<ToasterHeatingDisplay> {
    @Override
    public CategoryIdentifier<? extends ToasterHeatingDisplay> getCategoryIdentifier() {
        return RfmPlugin.TOASTER_HEATING;
    }

    @Override
    public Text getTitle() {
        return Utils.translation("jei_category", "toaster_heating");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.TOASTER_LIGHT.get());
    }

    @Override
    public List<Widget> setupDisplay(ToasterHeatingDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.x + 4, bounds.y + 4 + 10);
        DecimalFormat df = new DecimalFormat("###.##");
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget(DarkModeHelper.rfmJei1(startPoint.x, startPoint.y, 151, 0, 105, 57)));
        //        builder.addSlot(RecipeIngredientRole.INPUT, 18, 4).addIngredients(recipe.getIngredients().get(0));
//        builder.addSlot(RecipeIngredientRole.OUTPUT, 83, 19).addItemStack(Plugin.getResult(recipe));
//        this.arrow = this.helper.createAnimatedDrawable(this.helper.createDrawable(Plugin.TEXTURES, 93, 0, 24, 17), recipe.getTime(), IDrawableAnimated.StartDirection.LEFT, false);
        widgets.add(Widgets.createLabel(new Point(bounds.x + bounds.width - 5, bounds.y + 5), Text.translatable("category.rei.campfire.time", df.format(display.recipe.getTime() / 20d))).noShadow().rightAligned().color(0xFF404040, 0xFFBBBBBB));

        // 93, 0, 24, 17
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 18, startPoint.y + 4)).entries(display.getInputEntries().get(0)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 83, startPoint.y + 19)).entries(display.getOutputEntries().get(0)).markOutput().disableBackground());
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 52, startPoint.y + 19)).animationDurationTicks(display.recipe.getTime()));
        return widgets;
    }

    @Override
    public int getDisplayWidth(ToasterHeatingDisplay display) {
        return 105 + 8;
    }

    @Override
    public int getDisplayHeight() {
        return 57 + 8 + 10;
    }
}
