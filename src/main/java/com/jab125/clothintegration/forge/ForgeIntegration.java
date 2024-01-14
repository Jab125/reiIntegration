//#if LOADER<=FORGE
//$$ package com.jab125.clothintegration.forge;
//$$
//$$ import com.jab125.clothintegration.Integration;
//$$ import com.jab125.clothintegration.platform.ConfigScreenUtil;
//$$ import com.jab125.clothintegration.platform.Mod;
//$$ import net.minecraft.util.Identifier;
//$$ import net.minecraftforge.client.ConfigScreenHandler;
//$$ import net.minecraftforge.fml.ModContainer;
//$$ import net.minecraftforge.fml.ModList;
//$$ import net.minecraftforge.fml.config.ConfigTracker;
//$$ import net.minecraftforge.fml.config.ModConfig;
//$$ import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
//$$
//$$ import java.util.EnumMap;
//$$ import java.util.Set;
//$$
//$$ public class ForgeIntegration extends Integration {
//$$
//$$     public ForgeIntegration() {
//$$         super();
//$$         init();
//$$     }
//$$
//$$     private void init() {
//$$         ForgeCloth forgeCloth = new ForgeCloth();
//$$         ModList.get().forEachModContainer((s, container) -> {
//$$             if (getConfigs(container).isEmpty()) return;
//$$             if(container.getCustomExtension(ConfigScreenHandler.ConfigScreenFactory.class).isPresent()) return;
//$$             ConfigScreenUtil.addConfigScreenToMod(s, prev -> forgeCloth.createScreen(prev, s));
//$$         });
//$$     }
//$$
//$$     @Override
//$$     protected Identifier getId() {
//$$         return new Identifier("clothintegration:forge");
//$$     }
//$$
//$$     private static EnumMap<ModConfig.Type, ModConfig> getConfigs(ModContainer container) {
//$$         return ObfuscationReflectionHelper.getPrivateValue(ModContainer.class, container, "configs"); // mixin on forge is unreliable
//$$     }
//$$ }
//#endif