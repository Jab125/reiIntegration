//#if LOADER<=FORGE
//$$ package com.jab125.clothintegration.forge;
//$$
//$$ import com.jab125.clothintegration.Integration;
//$$ import com.jab125.clothintegration.platform.ConfigScreenUtil;
//$$ import com.jab125.clothintegration.platform.Mod;
//$$ import net.minecraft.util.Identifier;
//#if LOADER == NEO && MC >= 1.20.5
//$$ import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//#else
//$$ import net.minecraftforge.client.ConfigScreenHandler;
//#endif
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
//$$         return com.jab125.clothintegration.ConfigIntegration.id.id("roughlyenoughconfigscreens:forge");
//$$     }
//$$
//$$     private static EnumMap<ModConfig.Type, ModConfig> getConfigs(ModContainer container) {
//$$         return ObfuscationReflectionHelper.getPrivateValue(ModContainer.class, container, "configs"); // mixin on forge is unreliable
//$$     }
//$$ }
//#endif