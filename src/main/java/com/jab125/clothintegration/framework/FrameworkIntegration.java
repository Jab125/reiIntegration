package com.jab125.clothintegration.framework;

import com.jab125.clothintegration.Integration;
import com.jab125.clothintegration.platform.Mod;
import com.jab125.clothintegration.platform.PlatformUtil;
import com.mrcrayfish.framework.api.config.event.FrameworkConfigEvents;
import com.mrcrayfish.framework.config.FrameworkConfigManager;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameworkIntegration extends Integration {
	public FrameworkIntegration() {
		init();
	}

	private void init() {
		FrameworkCloth frameworkCloth = new FrameworkCloth();
		List<FrameworkConfigManager.FrameworkConfigImpl> configs = FrameworkConfigManager.getInstance().getConfigs();
		Map<String, List<FrameworkConfigManager.FrameworkConfigImpl>> theMap = new HashMap<>();
		for (FrameworkConfigManager.FrameworkConfigImpl config : configs) {
			theMap.putIfAbsent(config.getName().getNamespace(), new ArrayList<>());
			theMap.get(config.getName().getNamespace()).add(config);
		}
		for (Map.Entry<String, List<FrameworkConfigManager.FrameworkConfigImpl>> stringListEntry : theMap.entrySet()) {
			String modid = stringListEntry.getKey();
			frameworkCloth.createScreen(null, modid, stringListEntry.getValue());
		}
	}
	@Override
	protected Identifier getId() {
		return new Identifier("clothintegration:framework");
	}
}