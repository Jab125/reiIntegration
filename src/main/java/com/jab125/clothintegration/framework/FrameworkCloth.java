package com.jab125.clothintegration.framework;

import com.jab125.clothintegration.cloth.ButtonBuilder;
import com.jab125.clothintegration.mixin.AbstractConfigScreenAccessor;
import com.jab125.clothintegration.util.StringUtils;
import com.mrcrayfish.framework.api.config.*;
import com.mrcrayfish.framework.config.FrameworkConfigManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.AbstractConfigScreen;
import me.shedaniel.clothconfig2.impl.builders.AbstractFieldBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FrameworkCloth {
	public <T extends Enum<T>> Screen createScreen(Screen prev, String id, List<FrameworkConfigManager.FrameworkConfigImpl> impls) {
		Future future = new Future();
		ConfigBuilder configBuilder1 = ConfigBuilder.create();
		configBuilder1.setParentScreen(prev);
		configBuilder1.setTitle(Text.of(id));
		configBuilder1.setEditable(false);
		for (FrameworkConfigManager.FrameworkConfigImpl impl : impls) {
			ConfigBuilder configBuilder = ConfigBuilder.create();
			configBuilder1.setTitle(Text.of(impl.getFileName()));
			configBuilder1.setEditable(!impl.isReadOnly());
			future.setIfPresent(configBuilder);
			ConfigCategory category = configBuilder.getOrCreateCategory(Text.of(impl.getName().getPath()));
			Set<AbstractProperty<?>> allProperties = impl.getAllProperties();
			AbstractFieldBuilder<?, ?, ?> entry = null;
			for (AbstractProperty<?> property : allProperties) {
				if (property instanceof BoolProperty p) {
					entry = configBuilder.entryBuilder().startBooleanToggle(Text.of(p.getName()), p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
				}
				if (property instanceof DoubleProperty p) {
					entry = configBuilder.entryBuilder().startDoubleField(Text.of(p.getName()), p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
				} else if (property instanceof EnumProperty r) {
					EnumProperty<T> p = r;
					Class<T> clazz = p.getDefaultValue().getDeclaringClass();
					entry = configBuilder.entryBuilder().startEnumSelector(Text.of(p.getName()), clazz, p.getDefaultValue()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
				} else if (property instanceof IntProperty p) {
					entry = configBuilder.entryBuilder().startIntField(Text.of(p.getName()), p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
				} else if (property instanceof LongProperty p) {
					entry = configBuilder.entryBuilder().startLongField(Text.of(p.getName()), p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
				} else if (property instanceof StringProperty p) {
					entry = configBuilder.entryBuilder().startStrField(Text.of(p.getName()), p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
				} else if (property instanceof ListProperty p) {
					if (p.getType() == ListProperty.BOOL) {

					} else if (p.getType() == ListProperty.INT) {
						entry = configBuilder.entryBuilder().startIntList(Text.of(p.getName()), (List) p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
					} else if (p.getType() == ListProperty.DOUBLE) {
						entry = configBuilder.entryBuilder().startDoubleList(Text.of(p.getName()), (List) p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
					} else if (p.getType() == ListProperty.LONG) {
						entry = configBuilder.entryBuilder().startLongList(Text.of(p.getName()), (List) p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
					} else if (p.getType() == ListProperty.STRING) {
						entry = configBuilder.entryBuilder().startStrList(Text.of(p.getName()), (List) p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
					}
				} else {
					System.out.println("ERROR");
				}
				if (entry != null) {
					category.addEntry(entry.setTooltip(Text.of(property.getComment())).build());
				}
			}
			ConfigCategory main = configBuilder1.getOrCreateCategory(Text.of("Main"));
			AbstractConfigScreen build = (AbstractConfigScreen) configBuilder.build();
			main.addEntry(new ButtonBuilder(Text.literal(StringUtils.convertToSentence(impl.getName().getPath())).formatted(Formatting.BOLD), a -> MinecraftClient.getInstance().setScreen(build)).build());
			future.queue.add(build);
		}
		Screen build = configBuilder1.build();
		future.configScreen = build;
		future.spitQueue();
		return build;
	}

//	private static Screen createConfigScreen(ConfigBuilder prevConfigBuilder, AbstractProperty<?> property, String path, Future prevFuture) {
//
//	}

	private static class Future {
        Screen configScreen;
        List<AbstractConfigScreen> queue = new ArrayList<>();

        Screen spitQueue() {
            for (AbstractConfigScreen abstractConfigScreen : queue) {
                ((AbstractConfigScreenAccessor)abstractConfigScreen).setParent(configScreen);
            }
            queue.clear();
            return configScreen;
        }

        void setIfPresent(ConfigBuilder configBuilder) {
            if (configScreen != null) configBuilder.setParentScreen(configScreen);
        }
    }
}
