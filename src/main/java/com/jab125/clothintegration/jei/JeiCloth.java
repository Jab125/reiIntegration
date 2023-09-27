package com.jab125.clothintegration.jei;

import com.jab125.clothintegration.mixin.EnumSerializerAccessor;
import com.jab125.clothintegration.mixin.IntegerSerializerAccessor;
import com.jab125.clothintegration.util.StringUtils;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.AbstractFieldBuilder;
import mezz.jei.api.runtime.config.IJeiConfigCategory;
import mezz.jei.api.runtime.config.IJeiConfigFile;
import mezz.jei.api.runtime.config.IJeiConfigValue;
import mezz.jei.api.runtime.config.IJeiConfigValueSerializer;
//import mezz.jei.common.config.file.serializers.BooleanSerializer;
//import mezz.jei.common.util.MinecraftLocaleSupplier;
//import mezz.jei.library.config.serializers.ChatFormattingSerializer;
import mezz.jei.common.config.file.ConfigValue;
import mezz.jei.common.config.file.serializers.*;
import mezz.jei.library.config.serializers.ChatFormattingSerializer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.*;
import java.util.stream.Collectors;

public class JeiCloth {
    public JeiCloth() {
        init();
    }

    public void init() {

    }

    public <T extends Enum<T>> Screen createScreen(Screen prev) {
     //   MinecraftLocaleSupplier
        ConfigBuilder configBuilder = ConfigBuilder.create();
        ConfigEntryBuilder entryBuilder = configBuilder.entryBuilder();
        configBuilder.setParentScreen(prev);
        configBuilder.setTitle(Text.literal("Just Enough Items"));
        for (IJeiConfigFile configFile : JeiPlugin.manager.getConfigFiles().stream().sorted(Comparator.comparing(a -> a.getPath().getFileName().toString())).toList()) {
            ConfigCategory clothCategory2 = configBuilder.getOrCreateCategory(Text.literal(StringUtils.convertToSentence(configFile.getPath().getFileName().toString())));
            for (IJeiConfigCategory category : configFile.getCategories().stream().sorted(Comparator.comparing(IJeiConfigCategory::getName)).toList()) {
                var clothCategory = entryBuilder.startSubCategory(Text.of(category.getName()));
                for (IJeiConfigValue<?> configValue : category.getConfigValues()) {
                    IJeiConfigValueSerializer<?> serializer = configValue.getSerializer(); //config.jei.advanced.colorSearchEnabled
                    String h = configValue.getName();
                    h = Character.toLowerCase(h.charAt(0)) + h.substring(1);
                    Text title = Text.translatableWithFallback("config.jei." + category.getName() + "." + h, (String) StringUtils.convertToSentence(configValue.getName()));
                    AbstractFieldBuilder<?, ?, ?> entry = null;
                    if (serializer instanceof BooleanSerializer b) {
                        entry = entryBuilder.startBooleanToggle(title, (Boolean) configValue.getValue()).setDefaultValue((Boolean) configValue.getDefaultValue())
                                .setSaveConsumer(value -> ((IJeiConfigValue) configValue).set(value));
                    } else if (serializer instanceof IntegerSerializer integerSerializer && serializer instanceof IntegerSerializerAccessor accessor) {
                        entry = entryBuilder.startIntField(title, (Integer) configValue.getValue()).setDefaultValue((Integer) configValue.getDefaultValue())
                                .setMin(accessor.getMin())
                                .setMax(accessor.getMax())
                                .setSaveConsumer(value -> ((IJeiConfigValue) configValue).set(value));
                    } else if (serializer instanceof ChatFormattingSerializer chatFormattingSerializer) {
                        entry = entryBuilder.startStrField(title, chatFormattingSerializer.serialize((List<Formatting>) configValue.getValue())).setDefaultValue(chatFormattingSerializer.serialize((List<Formatting>) configValue.getDefaultValue())).setErrorSupplier((s) -> {
                            DeserializeResult<List<Formatting>> j = chatFormattingSerializer.deserialize(s);
                            if (!j.getErrors().isEmpty()) return Optional.of(Text.literal("Not Valid!"));
                            if (!chatFormattingSerializer.isValid(j.getResult().get())) return Optional.of(Text.literal("Not Valid!"));
                            return Optional.empty();
                        }).setSaveConsumer(value -> ((IJeiConfigValue) configValue).set(chatFormattingSerializer.deserialize(value).getResult().get()));
//                        entry = entryBuilder.startSelector(title, chatFormattingSerializer.getAllValidValues().orElse(Collections.emptyList()).toArray(), configValue.getValue()).build();
                    } else if (serializer instanceof EnumSerializer<?> listSerializer) {
                        if (listSerializer instanceof EnumSerializerAccessor accessor) {
                            entry = entryBuilder.startEnumSelector(title, (Class<T>)accessor.getEnumClass(), (T)configValue.getValue()).setDefaultValue((T)configValue.getDefaultValue()).setEnumNameProvider(StringUtils.enumToText(accessor.getEnumClass())).setSaveConsumer(value -> ((IJeiConfigValue) configValue).set(value));;
                        }
//                        entry = entryBuilder.startStrList(title, (List<String>) configValue.getValue());

                    } else if (serializer instanceof ListSerializer<?> listSerializer) {
                        IJeiConfigValueSerializer listValueSerializer = listSerializer.getListValueSerializer();
                        List<String> strList = new ArrayList<>();
                        List<String> strList2 = new ArrayList<>();
                        List value = (List) configValue.getValue();
                        for (Object o : value) {
                            strList.add(listValueSerializer.serialize(o));
                        }
                        List value2 = (List) configValue.getDefaultValue();
                        for (Object o : value2) {
                            strList2.add(listValueSerializer.serialize(o));
                        }
                        entry = entryBuilder.startStrList(title, strList).setDefaultValue(strList2)
                                .setErrorSupplier(val -> {
                                    try {
                                        String collect = val.stream().collect(Collectors.joining(", "));
                                        IJeiConfigValueSerializer.IDeserializeResult deserialize = serializer.deserialize(collect);
                                        if (!deserialize.getErrors().isEmpty())
                                            return Optional.of(Text.literal("Not Valid!"));
                                        if (!listSerializer.isValid((List) deserialize.getResult().get()))
                                            return Optional.of(Text.literal("Not Valid!"));
                                        return Optional.empty();
                                    } catch (Exception e) {
                                        return Optional.of(Text.literal("Not Valid!"));
                                    }
                                }).setSaveConsumer(val -> {
                                    try {
                                        String collect = val.stream().collect(Collectors.joining(", "));
                                        IJeiConfigValueSerializer.IDeserializeResult deserialize = serializer.deserialize(collect);
                                        ((ConfigValue) configValue).set((List) deserialize.getResult().get());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                });
                    }
//                    if (serializer instanceof ListSerializer<?> listSerializer) {
//                        IJeiConfigValueSerializer<T> listValueSerializer = (IJeiConfigValueSerializer<T>) listSerializer.getListValueSerializer();
//                        if (listValueSerializer instanceof EnumSerializer<T> enumSerializer && listValueSerializer instanceof EnumSerializerAccessor accessor) {
//                            entry = entryBuilder.startEnumSelector(title, (Class<T>)accessor.getEnumClass(), (T)configValue.getValue());
//                        }
////                        entry = entryBuilder.startStrList(title, (List<String>) configValue.getValue());
//
//                    }
                    if (entry != null) {
                        entry.setTooltipSupplier(a -> Optional.of(new MutableText[]{Text.literal(configValue.getDescription()), Text.literal(configValue.getSerializer().getValidValuesDescription())}));
                        clothCategory.add(entry.build());
                    } else {
                        System.out.println(serializer.getClass());
                    }
                }
                clothCategory2.addEntry(clothCategory.build());
            }
        }
//        configBuilder.setSavingRunnable(() -> {
//            for (IJeiConfigFile configFile : JeiPlugin.manager.getConfigFiles()) {
//                co
//            }
//        })
        return configBuilder.build();
    }

}
