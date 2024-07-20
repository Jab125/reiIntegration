package dev.jab125.reiintegration.plugin.rfm.client.categories;

import com.google.common.collect.Lists;
import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import com.mrcrayfish.furniture.refurbished.util.Utils;
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

@Deprecated
public class OldCuttingBoardCombiningCategory implements DisplayCategory<CuttingBoardCombiningDisplay> {
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
//        Point startPoint = new Point(bounds.x + 5, bounds.y + 5);
//        List<Widget> widgets = Lists.newArrayList();
//        widgets.add(Widgets.createRecipeBase(bounds));
//        List<EntryIngredient> ingredients = display.getInputEntries();
//        DefaultSmithingCategory
//        for(int i = 0; i < ingredients.size(); i++) {
//            widgets.add(Widgets.createSlot(new Point(startPoint.x + 25, startPoint.y /*+ 69*/ + i * 16)).entries(ingredients.get(i)));
//            //builder.addSlot(RecipeIngredientRole.INPUT, 25, 69 - i * 16).addIngredients(ingredients.get(i));
//        }
//        widgets.add(Widgets.createSlot(new Point(startPoint.x + 77, startPoint.y + (bounds.getHeight() - 10) / 2 - 8 /*+ 69*/)).entries(display.getOutputEntries().get(0)));
//        //builder.addSlot(RecipeIngredientRole.OUTPUT, 77, 69).addItemStack(Plugin.getResult(recipe));
//        return widgets;//DisplayCategory.super.setupDisplay(display, bounds);

        Point startPoint = new Point(bounds.getCenterX() - 31, bounds.getCenterY() - 13);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        boolean legacy = display.getInputEntries().size() <= 2;
        int offsetX = legacy ? 0 : 5;
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 46 + offsetX, startPoint.y + 4)));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 76 + offsetX, startPoint.y + 5)));
        int i = 0;
        for (EntryIngredient inputEntry : display.getInputEntries()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 28 - 18 * i + offsetX, startPoint.y + 5)).entries(inputEntry).markInput());
            i++;
        }
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 76 + offsetX, startPoint.y + 5)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        return widgets;
    }

    //    //public static final RecipeType<CuttingBoardCombiningRecipe> TYPE = RecipeType.register(Constants.MOD_ID, "cutting_board_combining", CuttingBoardCombiningRecipe.class);
//
//    private final IDrawable background;
//    private final IDrawable icon;
//
//    public CuttingBoardCombiningCategory(IGuiHelper helper)
//    {
//        this.background = helper.createDrawable(Plugin.TEXTURES, 157, 156, 99, 100);
//        this.icon = helper.createDrawableItemStack(new ItemStack(ModBlocks.CUTTING_BOARD_OAK.get()));
//    }
//
//    @Override
//    public RecipeType<CuttingBoardCombiningRecipe> getRecipeType()
//    {
//        return TYPE;
//    }
//
//    @Override
//    public Component getTitle()
//    {
//        return Utils.translation("jei_category", "cutting_board_combining");
//    }
//
//    @Override
//    public IDrawable getBackground()
//    {
//        return this.background;
//    }
//
//    @Override
//    public IDrawable getIcon()
//    {
//        return this.icon;
//    }
//
//    @Override
//    public void setRecipe(IRecipeLayoutBuilder builder, CuttingBoardCombiningRecipe recipe, IFocusGroup focuses)
//    {
//        NonNullList<Ingredient> ingredients = recipe.getIngredients();
//        for(int i = 0; i < ingredients.size(); i++)
//        {
//            builder.addSlot(RecipeIngredientRole.INPUT, 25, 69 - i * 16).addIngredients(ingredients.get(i));
//        }
//        builder.addSlot(RecipeIngredientRole.OUTPUT, 77, 69).addItemStack(Plugin.getResult(recipe));
//    }
}
