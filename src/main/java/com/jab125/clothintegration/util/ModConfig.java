package com.jab125.clothintegration.util;

import com.jab125.clothintegration.platform.ConfigScreenUtil;
import com.jab125.clothintegration.platform.ModConfigScreen;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

@Config(name = "recs")
public class ModConfig implements ConfigData {

	// required restart
	public List<String> integrations = new ArrayList<>(List.of(
			"roughlyenoughconfigscreens:jei",
			//#if LOADER <= FORGE
			//$$ "roughlyenoughconfigscreens:forge",
			//$$ "roughlyenoughconfigscreens:minecraft",
			//#endif
			"roughlyenoughconfigscreens:framework"
	));

	public boolean configuredBackgrounds = true;
	public boolean transparentBackgrounds = false;
	public boolean smoothScrolling = true;

	public static final ConfigHolder<ModConfig> MOD_CONFIG = AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);

	public static ModConfig $() {
		return MOD_CONFIG.get();
	}

	static {
		ConfigScreenUtil.addConfigScreenToMod("roughlyenoughconfigscreens", ModConfigScreen::getConfigScreen);
	}

	public static void a90() {

	}
}
