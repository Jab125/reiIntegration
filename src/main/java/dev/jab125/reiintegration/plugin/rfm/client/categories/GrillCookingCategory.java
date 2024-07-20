package dev.jab125.reiintegration.plugin.rfm.client.categories;

import com.google.common.collect.Lists;
import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import com.mrcrayfish.furniture.refurbished.core.ModItems;
import com.mrcrayfish.furniture.refurbished.util.Utils;
import dev.jab125.reiintegration.helper.DarkModeHelper;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import dev.jab125.reiintegration.plugin.rfm.common.displays.FryingPanCookingDisplay;
import dev.jab125.reiintegration.plugin.rfm.common.displays.GrillCookingDisplay;
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

public class GrillCookingCategory implements DisplayCategory<GrillCookingDisplay> {
    private final ItemStack campfireStack = new ItemStack(Items.CAMPFIRE);
    @Override
    public CategoryIdentifier<? extends GrillCookingDisplay> getCategoryIdentifier() {
        return RfmPlugin.GRILL_COOKING;
    }

    @Override
    public Text getTitle() {
        return Utils.translation("jei_category", "grill_cooking");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.GRILL_RED.get());
    }

    @Override
    public List<Widget> setupDisplay(GrillCookingDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.x + 4, bounds.y + 4 + 10);
        DecimalFormat df = new DecimalFormat("###.##");
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget(DarkModeHelper.rfmJei1(startPoint.x, startPoint.y, 135,  57, 121, 79)));
        widgets.add(Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {
            if (display.recipe.getType() == RecipeType.CAMPFIRE_COOKING) {
                int x = startPoint.x;
                int y = startPoint.y;
                graphics.fill(x + 99, y + 5, x + 99 + 16, y + 5 + 16, 0x33000000);
                graphics.drawItemWithoutEntity(this.campfireStack, x + 99, y + 5);
            }
        }));
        widgets.add(Widgets.createTooltip(() -> new Rectangle(startPoint.x + 103, startPoint.y + 5, 16, 16), Utils.translation("gui", "jei_campfire_info")));

        widgets.add(Widgets.createLabel(new Point(bounds.x + bounds.width - 5, bounds.y + 5), Text.translatable("category.rei.campfire.time", df.format(display.recipe.getTime() / 20d))).noShadow().rightAligned().color(0xFF404040, 0xFFBBBBBB));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 26, startPoint.y + 6)).entries(display.getInputEntries().get(0)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 99, startPoint.y + 31)).entries(display.getOutputEntries().get(0)).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 71, startPoint.y + 3)).entry(EntryStacks.of(ModItems.SPATULA.get())).disableHighlight().disableBackground());
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 68, startPoint.y + 31)).animationDurationTicks(display.recipe.getTime()));
        return widgets;
    }

    @Override
    public int getDisplayWidth(GrillCookingDisplay display) {
        return 121 + 8;
    }

    @Override
    public int getDisplayHeight() {
        return 79 + 8 + 10;
    }
}
