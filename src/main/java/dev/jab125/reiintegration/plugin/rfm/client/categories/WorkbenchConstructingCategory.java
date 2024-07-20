package dev.jab125.reiintegration.plugin.rfm.client.categories;

import com.google.common.collect.Lists;
import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import com.mrcrayfish.furniture.refurbished.util.Utils;
import dev.jab125.reiintegration.helper.DarkModeHelper;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import dev.jab125.reiintegration.plugin.rfm.common.displays.ToasterHeatingDisplay;
import dev.jab125.reiintegration.plugin.rfm.common.displays.WorkbenchConstructingDisplay;
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
import net.minecraft.util.math.MathHelper;

import java.text.DecimalFormat;
import java.util.List;

public class WorkbenchConstructingCategory implements DisplayCategory<WorkbenchConstructingDisplay> {
    @Override
    public CategoryIdentifier<? extends WorkbenchConstructingDisplay> getCategoryIdentifier() {
        return RfmPlugin.WORKBENCH_CONSTRUCTING;
    }

    @Override
    public Text getTitle() {
        return Utils.translation("jei_category", "workbench_constructing");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.WORKBENCH.get());
    }

    // /        this.slots.clear();
    ////        NonNullList<StackedIngredient> materials = recipe.getMaterials();
    ////        int left = 6;
    ////        int top = 6;
    ////        int slotSize = 18;
    ////        int boxSize = 54;
    ////        int width = slotSize * Mth.clamp(materials.size(), 1, 3);
    ////        int height = slotSize * Mth.clamp(Mth.ceil(materials.size() / (float) 3), 1, 3);
    ////        for(int i = 0; i < materials.size(); i++)
    ////        {
    ////            int x = left + (i % 3) * slotSize + (boxSize - width) / 2;
    ////            int y = top + (i / 3) * slotSize + (boxSize - height) / 2;
    ////            StackedIngredient material = materials.get(i);
    ////            List<ItemStack> stacks = Arrays.stream(material.ingredient().getItems()).map(stack -> {
    ////                ItemStack copy = stack.copy();
    ////                copy.setCount(material.count());
    ////                return copy;
    ////            }).toList();
    ////            builder.addSlot(RecipeIngredientRole.INPUT, x, y).addItemStacks(stacks);
    ////            this.slots.add(Pair.of(new Vector2i(x - 1, y - 1), this.helper.createDrawable(Plugin.TEXTURES_2, 0, 64, 18, 18)));
    ////        }
    ////        builder.addSlot(RecipeIngredientRole.OUTPUT, 96, 24).addItemStack(Plugin.getResult(recipe));
    @Override
    public List<Widget> setupDisplay(WorkbenchConstructingDisplay display, Rectangle bounds) {
        // 0, 0, 118, 64
        Point startPoint = new Point(bounds.x + 4, bounds.y + 4);
        DecimalFormat df = new DecimalFormat("###.##");
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget(DarkModeHelper.rfmJei2(startPoint.x, startPoint.y, 0, 0, 118, 64)));
        int left = startPoint.x + 6;
        int top = startPoint.y + 6;
        //Point sp = new Point(startPoint.x + left, startPoint.y + top);
        int slotSize = 18;
        int boxSize = 54;
        List<EntryIngredient> inputEntries = display.getInputEntries();
        int width = slotSize * MathHelper.clamp(inputEntries.size(), 1, 3);
        int height = slotSize * MathHelper.clamp(MathHelper.ceil(inputEntries.size() / (float) 3), 1, 3);
        for (int i = 0; i < inputEntries.size(); i++) {
            int x = left + (i % 3) * slotSize + (boxSize - width) / 2;
            int y = top + (i / 3) * slotSize + (boxSize - height) / 2;
            EntryIngredient entryStacks = inputEntries.get(i);
            widgets.add(Widgets.createDrawableWidget(DarkModeHelper.rfmJei2a(x - 1, y + 11, 0, 76, 18, 6)));
            widgets.add(Widgets.createSlot(new Point(x, y)).entries(entryStacks).markInput().disableBackground());
        }
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 96, startPoint.y + 24)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());


//        // 93, 0, 24, 17
//        widgets.add(Widgets.createSlot(new Point(startPoint.x + 18, startPoint.y + 4)).entries(display.getInputEntries().get(0)).markInput().disableBackground());
//        widgets.add(Widgets.createSlot(new Point(startPoint.x + 83, startPoint.y + 19)).entries(display.getOutputEntries().get(0)).markOutput().disableBackground());
//        widgets.add(Widgets.createArrow(new Point(startPoint.x + 52, startPoint.y + 19)).animationDurationTicks(display.recipe.getTime()));
        return widgets;
    }

    @Override
    public int getDisplayWidth(WorkbenchConstructingDisplay display) {
        return 118 + 8;
    }

    @Override
    public int getDisplayHeight() {
        return 64 + 8;
    }
}
