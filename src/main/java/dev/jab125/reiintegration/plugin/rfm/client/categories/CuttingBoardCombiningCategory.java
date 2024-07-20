package dev.jab125.reiintegration.plugin.rfm.client.categories;

import com.google.common.collect.Lists;
import com.mrcrayfish.furniture.refurbished.compat.jei.Plugin;
import com.mrcrayfish.furniture.refurbished.compat.jei.categories.CuttingBoardSlicingCategory;
import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import com.mrcrayfish.furniture.refurbished.util.Utils;
import dev.jab125.reiintegration.helper.DarkModeHelper;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import dev.jab125.reiintegration.plugin.rfm.common.displays.CuttingBoardCombiningDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;

import java.util.List;

public class CuttingBoardCombiningCategory implements DisplayCategory<CuttingBoardCombiningDisplay> {
    @Override
    public CategoryIdentifier<? extends CuttingBoardCombiningDisplay> getCategoryIdentifier() {
        return RfmPlugin.CUTTING_BOARD_COMBINING;
    }

    @Override
    public Text getTitle() {
        return Utils.translation("jei_category", "cutting_board_combining");
    }

    @Override
    public Renderer getIcon() {// Plugin.TEXTURES, 157, 156, 99, 100
        return EntryStacks.of(ModBlocks.CUTTING_BOARD_OAK.get());
    }

    @Override
    public List<Widget> setupDisplay(CuttingBoardCombiningDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.x + 4, bounds.y + 4);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget(DarkModeHelper.rfmJei1(startPoint.x, startPoint.y, 157, 156, 99, 100)));
        List<EntryIngredient> ingredients = display.getInputEntries();
        for(int i = 0; i < ingredients.size(); i++) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 25, startPoint.y + 69 - i * 16)).markInput().entries(ingredients.get(i)).disableBackground());
        }
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 77, startPoint.y + 69)).entries(display.getOutputEntries().get(0)).disableBackground());
        return widgets;
    }

    @Override
    public int getDisplayWidth(CuttingBoardCombiningDisplay display) {
        return 99 + 8;
    }

    @Override
    public int getDisplayHeight() {
        return 100 + 8;
    }
}
