package com.jab125.clothintegration.util;

import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Function;

public class StringUtils {
    public static String convertToSentence(String camelCaseString) {
        if (camelCaseString == null || camelCaseString.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < camelCaseString.length(); i++) {
            char currentChar = camelCaseString.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                if (result.length() > 0) {
                    result.append(" ");
                }
                result.append(Character.toLowerCase(currentChar));
            } else {
                result.append(currentChar);
            }
        }

        // Capitalize the first letter
        result.setCharAt(0, Character.toUpperCase(result.charAt(0)));

        return result.toString();
    }

    public static Function<Enum, Text> enumToText(Class<?> clazz) {
        return a -> {
            if (a instanceof SelectionListEntry.Translatable t) {
                return Text.translatable(t.getKey());
            } else {
                if (a == null) return Text.literal("null");
                if (Formatting.class.equals(clazz)) {
                    return Text.literal(((Formatting)a).getName()).formatted((Formatting)a);
                }
            }
            return Text.literal(a.name());
        };
    }
    public static Function<Enum, Text> enumToText() {
        return enumToText(null);
    }
}
