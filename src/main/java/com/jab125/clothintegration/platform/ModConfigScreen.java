package com.jab125.clothintegration.platform;

import com.jab125.clothintegration.util.ModConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.entries.StringListListEntry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModConfigScreen {
	public static Screen getConfigScreen(Screen prev) {
		ModConfig config = ModConfig.MOD_CONFIG.get();
		ModConfig defaultConfig = new ModConfig();
		ConfigBuilder configBuilder = ConfigBuilder.create();
		PlatformUtil.getMod("roughlyenoughconfigscreens").flatMap(PlatformUtil::getConfiguredBackground).ifPresent(a -> PlatformUtil.setBackgroundTexture(configBuilder, a));
		ConfigCategory client = configBuilder.getOrCreateCategory(Text.literal("Client"));
		StringListListEntry entry = configBuilder.entryBuilder().startStrList(Text.literal("Blacklisted Integrations"), config.blacklistedIntegrations)
				.setDefaultValue(defaultConfig.blacklistedIntegrations)
				.requireRestart()
				.setCellErrorSupplier(a -> {
					Identifier identifier = Identifier.tryParse(a);
					if (identifier == null) {
						return Optional.of(Text.literal("\"" + a + "\" is not a valid identifier!"));
					}
					return Optional.empty();
				})
				.setSaveConsumer(list -> config.blacklistedIntegrations = list).build();
		client.addEntry(entry);
		client.addEntry(configBuilder.entryBuilder().startBooleanToggle(Text.literal("Configured Backgrounds"), config.configuredBackgrounds).setDefaultValue(defaultConfig.configuredBackgrounds).setSaveConsumer(a -> config.configuredBackgrounds = a).build());
		client.addEntry(configBuilder.entryBuilder().startBooleanToggle(Text.literal("Smooth Scrolling"), config.smoothScrolling).setDefaultValue(defaultConfig.smoothScrolling).setSaveConsumer(a -> config.smoothScrolling = a).build());
		client.addEntry(configBuilder.entryBuilder().startTextDescription(Text.literal("Integrations has been replaces by Blacklisted Integrations.\nYou may have to manually re-add blacklisted integrations.")).build());
		configBuilder.setSavingRunnable(ModConfig.MOD_CONFIG::save);
		configBuilder.setParentScreen(prev);
		return configBuilder.build();
	}
}
