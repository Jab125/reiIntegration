package dev.jab125.reiintegration.plugin.rfm.client.categories;

import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import com.mrcrayfish.furniture.refurbished.platform.Services;
import com.mrcrayfish.furniture.refurbished.util.Utils;
import dev.jab125.reiintegration.helper.DarkModeHelper;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import dev.jab125.reiintegration.plugin.rfm.common.displays.CuttingBoardSlicingDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CuttingBoardSlicingCategory implements DisplayCategory<CuttingBoardSlicingDisplay> {
    @Override
    public CategoryIdentifier<? extends CuttingBoardSlicingDisplay> getCategoryIdentifier() {
        return RfmPlugin.CUTTING_BOARD_SLICING;
    }

    @Override
    public Text getTitle() {
        return Utils.translation("jei_category", "cutting_board_slicing");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CUTTING_BOARD_OAK.get());
    }

    @Override
    public List<Widget> setupDisplay(CuttingBoardSlicingDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.x + 4, bounds.y + 4);
        List<Widget> w = new ArrayList<>();
        w.add(Widgets.createRecipeBase(bounds));
        w.add(Widgets.createDrawableWidget(DarkModeHelper.rfmJei1(startPoint.x, startPoint.y, 0, 36, 133, 36)));
        w.add(Widgets.createSlot(new Point(startPoint.x + 26, startPoint.y + 6)).entries(display.getInputEntries().get(0)).markInput().disableBackground());
        w.add(Widgets.createSlot(new Point(startPoint.x + 111, startPoint.y + 10)).entries(display.getOutputEntries().get(0)).markOutput().disableBackground());
        w.add(Widgets.createSlot(new Point(startPoint.x + 73, startPoint.y + 11)).entries(EntryIngredients.ofItemTag(Services.TAG.getToolKnivesTag())).disableBackground());
        return w;
    }

    @Override
    public int getDisplayWidth(CuttingBoardSlicingDisplay display) {
        return 133 + 8;
    }

    @Override
    public int getDisplayHeight() {
        return 36 + 8;
    }
}
