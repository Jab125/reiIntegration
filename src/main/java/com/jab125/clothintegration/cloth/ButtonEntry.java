package com.jab125.clothintegration.cloth;

import me.shedaniel.clothconfig2.gui.entries.TooltipListEntry;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ButtonEntry extends TooltipListEntry<Text> {

    private final ButtonWidget buttonWidget;

    public ButtonEntry(Text fieldName, @Nullable Supplier<Optional<Text[]>> tooltipSupplier, Consumer<ButtonEntry> onPress) {
        super(fieldName, tooltipSupplier);
        buttonWidget = ButtonWidget.builder(fieldName, a -> onPress.accept(this))
                .dimensions(0, 0, 15, 20)
                .build();
    }

    @Override
    public Optional<Text> getDefaultValue() {
        return Optional.empty();
    }

    @Override
    public Text getValue() {
        return null;
    }

    @Override
    public List<? extends Selectable> narratables() {
        return Collections.emptyList();
    }

    @Override
    public List<? extends Element> children() {
        return List.of();
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void render(
            //#if MC>=1.20.0
            DrawContext
            //#else
            //$$ MatrixStack
            //#endif
                                   graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
        super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
        if (enableRequirement != null) {
            buttonWidget.active = enableRequirement.check();
        }
        buttonWidget.setX(x);
        buttonWidget.setY(y);
        buttonWidget.setWidth(entryWidth);
        //#if MC>=1.20.0
        // buttonWidget.setHeight(20);
        //#endif
        buttonWidget.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double d, double e, int i) {
        buttonWidget.mouseClicked(d, e, i);
        return super.mouseClicked(d, e, i);
    }
}
