//#if HAS:FRAMEWORK
package com.jab125.clothintegration.framework;

import com.jab125.clothintegration.cloth.ButtonBuilder;
import com.jab125.clothintegration.mixin.AbstractConfigScreenAccessor;
import com.jab125.clothintegration.platform.Mod;
import com.jab125.clothintegration.platform.PlatformUtil;
import com.jab125.clothintegration.util.ModConfig;
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
		configBuilder1.setShouldListSmoothScroll(ModConfig.$().smoothScrolling);
		configBuilder1.setShouldTabsSmoothScroll(ModConfig.$().smoothScrolling);
		configBuilder1.setParentScreen(prev);
		configBuilder1.setTitle(Text.of(PlatformUtil.getMod(id).map(Mod::getName).orElse(id)));
		configBuilder1.setEditable(false);
		for (FrameworkConfigManager.FrameworkConfigImpl impl : impls) {
			ConfigBuilder configBuilder = ConfigBuilder.create();
			configBuilder.setShouldListSmoothScroll(ModConfig.$().smoothScrolling);
			configBuilder.setShouldTabsSmoothScroll(ModConfig.$().smoothScrolling);
			configBuilder.setTitle(Text.empty().append(configBuilder1.getTitle()).append(" / ").append(StringUtils.convertToSentence(impl.getName().getPath())));
			configBuilder.setEditable(!impl.isReadOnly());
			future.setIfPresent(configBuilder);
			ConfigCategory category = configBuilder.getOrCreateCategory(Text.of(impl.getName().getPath()));
			if (impl.isLoaded()) {
				Set<AbstractProperty<?>> allProperties = impl.getAllProperties();
				AbstractFieldBuilder<?, ?, ?> entry = null;
				for (AbstractProperty<?> property : allProperties) {
					if (property instanceof BoolProperty p) {
						entry = configBuilder.entryBuilder().startBooleanToggle(translate(p), p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
					} else if (property instanceof DoubleProperty p) {
						entry = configBuilder.entryBuilder().startDoubleField(translate(p), p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
					} else if (property instanceof EnumProperty r) {
						EnumProperty<T> p = r;
						Class<T> clazz = p.getDefaultValue().getDeclaringClass();
						entry = configBuilder.entryBuilder().startEnumSelector(translate(p), clazz, p.getDefaultValue()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
					} else if (property instanceof IntProperty p) {
						entry = configBuilder.entryBuilder().startIntField(translate(p), p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
					} else if (property instanceof LongProperty p) {
						entry = configBuilder.entryBuilder().startLongField(translate(p), p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
					} else if (property instanceof StringProperty p) {
						entry = configBuilder.entryBuilder().startStrField(translate(p), p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
					} else if (property instanceof ListProperty p) {
						if (p.getType() == ListProperty.BOOL) {

						} else if (p.getType() == ListProperty.INT) {
							entry = configBuilder.entryBuilder().startIntList(translate(p), (List) p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
						} else if (p.getType() == ListProperty.DOUBLE) {
							entry = configBuilder.entryBuilder().startDoubleList(translate(p), (List) p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
						} else if (p.getType() == ListProperty.LONG) {
							entry = configBuilder.entryBuilder().startLongList(translate(p), (List) p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
						} else if (p.getType() == ListProperty.STRING) {
							entry = configBuilder.entryBuilder().startStrList(translate(p), (List) p.get()).setDefaultValue(p.getDefaultValue()).setSaveConsumer(p::set).setErrorSupplier(a -> Optional.ofNullable(!p.isValid(a) ? Text.literal("ERROR") : null));
						}
					} else {
						System.out.println("ERROR type of entry is " + property.getClass());
					}
					if (entry != null) {
						category.addEntry(entry.setTooltip(Text.of(property.getComment())).build());
					}
				}
				ConfigCategory main = configBuilder1.getOrCreateCategory(Text.of("Main"));
				AbstractConfigScreen build = (AbstractConfigScreen) configBuilder.build();
				main.addEntry(new ButtonBuilder(Text.literal(StringUtils.convertToSentence(impl.getName().getPath())).formatted(Formatting.BOLD), a -> MinecraftClient.getInstance().setScreen(build)).build());
				future.queue.add(build);
			} else {
				ConfigCategory main = configBuilder1.getOrCreateCategory(Text.of("Main"));
				AbstractConfigScreen build = (AbstractConfigScreen) configBuilder.build();
				main.addEntry(new ButtonBuilder(Text.literal(StringUtils.convertToSentence(impl.getName().getPath())).formatted(Formatting.BOLD), a -> {}).setRequirement(() -> false).build());
				future.queue.add(build);
			}
		}
		Screen build = configBuilder1.build();
		future.configScreen = build;
		future.spitQueue();
		return build;
	}

	private Text translate(AbstractProperty<?> p) {
		String f = "";
		for (String s : p.getPath()) {
			if (!"common".equals(s)) // TODO special casing
			f += s + "/";
		}
		f = f.substring(0, f.length()-1);
		return Text.translatableWithFallback(p.getTranslationKey(), f);
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
//#endif