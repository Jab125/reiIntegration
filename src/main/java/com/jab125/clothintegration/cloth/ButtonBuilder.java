package com.jab125.clothintegration.cloth;

import me.shedaniel.clothconfig2.gui.entries.TextListEntry;
import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ButtonBuilder extends FieldBuilder<Text, ButtonEntry, ButtonBuilder> {

    private final Consumer<ButtonEntry> onClick;

    public ButtonBuilder(Text fieldNameKey, Consumer<ButtonEntry> onClick) {
        super(Text.empty(), fieldNameKey);
        this.onClick = onClick;
    }


    @Override
    public @NotNull ButtonEntry build() {
        return (ButtonEntry) this.finishBuilding(new ButtonEntry(this.getFieldNameKey(), null, onClick));
    }
}
