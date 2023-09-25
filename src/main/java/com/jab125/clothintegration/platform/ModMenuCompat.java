//#if LOADER >= FABRIC
//$$ package com.jab125.clothintegration.platform;
//$$
//$$ import com.jab125.clothintegration.ConfigIntegration;
//$$ import com.terraformersmc.modmenu.api.ConfigScreenFactory;
//$$ import com.terraformersmc.modmenu.api.ModMenuApi;
//$$ import net.minecraft.client.gui.screen.Screen;
//$$ import org.jetbrains.annotations.NotNull;
//$$
//$$ import java.util.HashMap;
//$$ import java.util.Map;
//$$ import java.util.function.Function;
//$$ import java.util.function.Supplier;
//$$
//$$ public class ModMenuCompat implements ModMenuApi {
//$$     @Override
//$$     public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
//$$         ConfigIntegration.init();
//$$         Map<String, ConfigScreenFactory<?>> factoryMap = new HashMap<>();
//$$         for (Map.Entry<String, Function<Screen, Screen>> stringFunctionEntry : ConfigScreenUtil.configScreenMap.entrySet()) {
//$$             factoryMap.putIfAbsent(stringFunctionEntry.getKey(), v -> stringFunctionEntry.getValue().apply(v));
//$$         }
//$$         return factoryMap;
//$$     }
//$$ }
//#endif