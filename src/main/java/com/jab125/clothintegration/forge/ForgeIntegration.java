//#if LOADER<=FORGE
//$$ package com.jab125.clothintegration.forge;
//$$
//$$ import com.jab125.clothintegration.Integration;
//$$ import com.jab125.clothintegration.platform.ConfigScreenUtil;
//$$ import com.jab125.clothintegration.platform.Mod;
//$$ import net.minecraft.util.Identifier;
//#if LOADER == NEO && MC >= 1.20.5
//$$ import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//#if MC >= 1.21
//$$ import net.neoforged.fml.config.ConfigTracker;
//$$ import java.util.concurrent.ConcurrentHashMap;
//#endif
//#else
//$$ import net.minecraftforge.client.ConfigScreenHandler;
//#endif
//$$ import net.minecraftforge.fml.ModContainer;
//$$ import net.minecraftforge.fml.ModList;
//$$ import net.minecraftforge.fml.config.ModConfig;
//$$ import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
//$$
//$$ import java.util.EnumMap;
//$$ import java.util.List;
//$$ import java.util.Map;
//$$ import java.util.Set;
//$$
//$$ public class ForgeIntegration extends Integration {
//$$
//$$     @Override
//$$     public void init() {
//$$         ForgeCloth forgeCloth = new ForgeCloth();
//$$         ModList.get().forEachModContainer((s, container) -> {
//$$             if (getConfigs(container).isEmpty()) return;
                 //#if MC < 1.20.5 || LOADER == FORGE
                 //$$ if(container.getCustomExtension(ConfigScreenHandler.ConfigScreenFactory.class).isPresent()) return;
                 //#else
                 //$$ if(container.getCustomExtension(IConfigScreenFactory.class).isPresent()) return;
                 //#endif
//$$             ConfigScreenUtil.addConfigScreenToMod(s, prev -> forgeCloth.createScreen(prev, s));
//$$         });
//$$     }
//$$
//$$     @Override
//$$     protected Identifier getId() {
//$$         return com.jab125.clothintegration.ConfigIntegration.id("roughlyenoughconfigscreens:forge");
//$$     }
//$$
//$$     private static Map<ModConfig.Type, ModConfig> getConfigs(ModContainer container) {
        //#if MC < 1.21 || LOADER == FORGE
        //$$ return ObfuscationReflectionHelper.getPrivateValue(ModContainer.class, container, "configs"); // mixin on forge is unreliable
        //#else
        //$$ return fromList(((ConcurrentHashMap<String, List<ModConfig>>)ObfuscationReflectionHelper.getPrivateValue(ConfigTracker.class, ConfigTracker.INSTANCE, "configsByMod")).get(container.getModId()));
        //#endif
//$$     }
//$$
//$$     private static Map<ModConfig.Type, ModConfig> fromList(List<ModConfig> configs) {
//$$         if (configs == null || configs.isEmpty()) {
//$$             return Map.of(); // Safe, since this is Java 21, not Java 8.
//$$         }
//$$         EnumMap<ModConfig.Type, ModConfig> map = new EnumMap<>(ModConfig.Type.class);
//$$         for (ModConfig config : configs) {
//$$             map.put(config.getType(), config);
//$$         }
//$$         return map;
//$$     }
//$$ }
//#endif