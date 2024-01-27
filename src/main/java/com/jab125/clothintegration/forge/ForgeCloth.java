//#if LOADER<=FORGE
//$$ package com.jab125.clothintegration.forge;
//$$
//$$ import com.electronwill.nightconfig.core.AbstractConfig;
//$$ import com.electronwill.nightconfig.core.UnmodifiableConfig;
//$$ import com.jab125.clothintegration.cloth.ButtonBuilder;
//$$ import com.jab125.clothintegration.mixin.AbstractConfigScreenAccessor;
//$$ import com.jab125.clothintegration.platform.PlatformUtil;
//$$ import com.jab125.clothintegration.util.Pair;
//$$ import com.jab125.clothintegration.util.StringUtils;
//$$ import me.shedaniel.clothconfig2.api.ConfigBuilder;
//$$ import me.shedaniel.clothconfig2.api.ConfigCategory;
//$$ import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
//$$ import me.shedaniel.clothconfig2.gui.AbstractConfigScreen;
//$$ import me.shedaniel.clothconfig2.impl.builders.AbstractFieldBuilder;
//$$ import net.minecraft.client.MinecraftClient;
//$$ import net.minecraft.client.gui.screen.Screen;
//$$ import net.minecraft.text.MutableText;
//$$ import net.minecraft.text.Text;
//$$ import net.minecraft.util.Formatting;
//$$ import net.minecraftforge.common.ForgeConfigSpec;
//$$ import net.minecraftforge.fml.ModContainer;
//$$ import net.minecraftforge.fml.ModList;
//$$ import net.minecraftforge.fml.config.ConfigTracker;
//$$ import net.minecraftforge.fml.config.ModConfig;
//$$ import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
//$$
//$$ import java.util.*;
//$$
//$$ public class ForgeCloth {
//$$     public Screen createScreen(Screen prev, String id) {
//$$         ModContainer modContainer = ModList.get().getModContainerById(id).get();
//$$         //if(modContainer.getCustomExtension(ConfigScreenHandler.ConfigScreenFactory.class).isPresent()) re
//$$         Set<ModConfig> configs = getConfigs(id);
//$$         return createConfigScreen(configs, modContainer, prev);
//$$     }
//$$
//$$     private static Screen createConfigScreen(Set<ModConfig> configs, ModContainer container, Screen prev) {
//$$         Future future = new Future();
//$$         ConfigBuilder configBuilder = ConfigBuilder.create();
//$$         PlatformUtil.getMod(container.getModId()).flatMap(PlatformUtil::getConfiguredBackground).ifPresent(configBuilder::setDefaultBackgroundTexture);
//$$         configBuilder.setShouldListSmoothScroll(com.jab125.clothintegration.util.ModConfig.$().smoothScrolling);
//$$         configBuilder.setShouldTabsSmoothScroll(com.jab125.clothintegration.util.ModConfig.$().smoothScrolling);
//$$         configBuilder.setParentScreen(prev);
//$$         configBuilder.setTitle(Text.of(container.getModInfo().getDisplayName()));
//$$         configBuilder.setEditable(false);
//$$         ConfigEntryBuilder entryBuilder = configBuilder.entryBuilder();
//$$         ConfigCategory main = configBuilder.getOrCreateCategory(Text.literal("Main"));
//$$         for (ModConfig config : configs.stream().sorted(Comparator.comparingInt(a -> a.getType().ordinal())).toList()) {
//$$             if (config.getType() == ModConfig.Type.SERVER && !MinecraftClient.getInstance().isIntegratedServerRunning()) {
//$$                 main.addEntry(new ButtonBuilder(Text.literal(getStrFor(config.getType())).formatted(Formatting.BOLD), a -> {}).setRequirement(() -> false).build());
//$$             } else {
//$$                 main.addEntry(new ButtonBuilder(Text.literal(getStrFor(config.getType())).formatted(Formatting.BOLD), a -> MinecraftClient.getInstance().setScreen(createConfigFor(configBuilder, config, container.getModInfo().getDisplayName() + " / " + getStrFor(config.getType()), future))).build());
//$$             }
//$$         }
//$$         future.configScreen = configBuilder.build();
//$$         return future.spitQueue();
//$$     }
//$$
//$$     private static String getStrFor(ModConfig.Type type) {
//$$         return switch (type) {
//$$             case CLIENT -> "Client";
//$$             case COMMON -> "Common";
//$$             case SERVER -> "Server";
//$$         };
//$$     }
//$$
//$$     private static class Future {
//$$         Screen configScreen;
//$$         List<AbstractConfigScreen> queue = new ArrayList<>();
//$$
//$$         Screen spitQueue() {
//$$             for (AbstractConfigScreen abstractConfigScreen : queue) {
//$$                 ((AbstractConfigScreenAccessor)abstractConfigScreen).setParent(configScreen);
//$$             }
//$$             queue.clear();
//$$             return configScreen;
//$$         }
//$$
//$$         void setIfPresent(ConfigBuilder configBuilder) {
//$$             if (configScreen != null) configBuilder.setParentScreen(configScreen);
//$$         }
//$$     }
//$$
//$$     private static Screen createConfigFor(ConfigBuilder prevConfigBuilder, ModConfig config, String path, Future prevFuture) {
//$$         Future future = new Future();
//$$         ConfigBuilder configBuilder = ConfigBuilder.create();
//$$         PlatformUtil.getMod(config.getModId()).flatMap(PlatformUtil::getConfiguredBackground).ifPresent(configBuilder::setDefaultBackgroundTexture);
//$$         configBuilder.setShouldListSmoothScroll(com.jab125.clothintegration.util.ModConfig.$().smoothScrolling);
//$$         configBuilder.setShouldTabsSmoothScroll(com.jab125.clothintegration.util.ModConfig.$().smoothScrolling);
//$$         prevFuture.setIfPresent(configBuilder);
//$$         ConfigEntryBuilder entryBuilder = configBuilder.entryBuilder();
//$$         configBuilder.setTitle(Text.of(path));
//$$         ConfigCategory main = configBuilder.getOrCreateCategory(Text.of("Main"));
//$$         List<Pair<ForgeConfigSpec.ConfigValue<?>, ForgeConfigSpec.ValueSpec>> list = new ArrayList<>();
//$$       //  ((ForgeConfigSpec) config.getSpec()).build();
//$$     //    ForgeConfigSpec spec = (ForgeConfigSpec) config.getSpec();
//$$       //  .. spec.
//$$         getForgeConfigFields(list, ((ForgeConfigSpec) config.getSpec()).getValues(), (ForgeConfigSpec) config.getSpec());
//$$         for (Pair<ForgeConfigSpec.ConfigValue<?>, ForgeConfigSpec.ValueSpec> pair : list) {
//$$             try {
//$$                 ForgeConfigSpec.ConfigValue<?> left = pair.left();
//$$                 ForgeConfigSpec.ValueSpec right = pair.right();
//$$                 String tk = right.getTranslationKey();
//$$                 Text title = Text.translatableWithFallback(tk != null ? tk : "config." + left.getPath().get(left.getPath().size()-1), StringUtils.convertToSentence(left.getPath().get(left.getPath().size()-1)));
//$$                 AbstractFieldBuilder<?, ?, ?> entry = null;
//$$                 if (Boolean.class.equals(right.getClazz()) || left instanceof ForgeConfigSpec.BooleanValue) {
//$$                     entry = entryBuilder.startBooleanToggle(title, (boolean) left.get()).setDefaultValue((Boolean) right.getDefault()).setSaveConsumer(a -> ((ForgeConfigSpec.ConfigValue)left).set(a));
//$$                 } else if (String.class.equals(right.getClazz())) {
//$$                     entry = entryBuilder.startStrField(title, (String) left.get()).setDefaultValue((String) right.getDefault()).setSaveConsumer(a -> ((ForgeConfigSpec.ConfigValue)left).set(a));
//$$                 } else if (Integer.class.equals(right.getClazz()) || left instanceof ForgeConfigSpec.IntValue) {
//$$                     entry = entryBuilder.startIntField(title, (int) left.get()).setDefaultValue((int) right.getDefault()).setSaveConsumer(a -> ((ForgeConfigSpec.ConfigValue)left).set(a));
//$$                 } else if (Float.class.equals(right.getClazz())) {
//$$                     entry = entryBuilder.startFloatField(title, (float) left.get()).setDefaultValue((float) right.getDefault()).setSaveConsumer(a -> ((ForgeConfigSpec.ConfigValue)left).set(a));
//$$                 } else if (Double.class.equals(right.getClazz()) || left instanceof ForgeConfigSpec.DoubleValue) {
//$$                     entry = entryBuilder.startDoubleField(title, (double) left.get()).setDefaultValue((double) right.getDefault()).setSaveConsumer(a -> ((ForgeConfigSpec.ConfigValue)left).set(a));
//$$                 } else if (left instanceof ForgeConfigSpec.EnumValue) {
//$$                     entry = entryBuilder.startEnumSelector(title, (Class)getEnumClass((ForgeConfigSpec.EnumValue<?>)left), (Enum)((ForgeConfigSpec.EnumValue) left).get()).setEnumNameProvider(StringUtils.enumToText(getEnumClass((ForgeConfigSpec.EnumValue<?>)left))).setDefaultValue((Enum)((ForgeConfigSpec.EnumValue) left).getDefault()).setSaveConsumer(a -> ((ForgeConfigSpec.ConfigValue)left).set(a));
//$$                 } else {
//$$                     System.out.println("NONE FOR: " + right.getClazz());
//$$                 }
//$$                 if (entry != null) {
//$$                     entry.setErrorSupplier(v -> {
//$$                         if (!right.test(v)) {
//$$                             return Optional.of(Text.literal("Invalid config value!"));
//$$                         }
//$$                         return Optional.empty();
//$$                     });
//$$                     main.addEntry(entry.setTooltip(Text.translatable(right.getComment())).build());
//$$                 } else {
//$$                     main.addEntry(entryBuilder.startTextDescription(Text.empty().formatted(Formatting.RED).append(title.copy().formatted(Formatting.BOLD)).append(" can't be modified in config yet!")).setTooltip(Text.of(right.getComment()), Text.literal("Report to @jab125 on Discord!").formatted(Formatting.RED)).build());
//$$                 }
//$$                 //main.addEntry(entryBuilder.startTextField(Text.translatable(right.getTranslationKey()), left.get().toString()).build());
//$$             }catch (Exception e){e.printStackTrace();}
//$$         }
//$$         configBuilder.setSavingRunnable(config::save);
//$$         return queueConfigScreen(prevFuture, configBuilder.build());
//$$         //ConfigCategory configCategory = configBuilder.getOrCreateCategory(Text.of(config.getFileName()));
//$$     }
//$$
//$$     private static Class<?> getEnumClass(ForgeConfigSpec.EnumValue<?> enumValue) {
//$$         return ObfuscationReflectionHelper.getPrivateValue(ForgeConfigSpec.EnumValue.class, enumValue, "clazz");
//$$     }
//$$
//$$     private static Screen queueConfigScreen(Future future, Screen built) {
//$$         future.queue.add((AbstractConfigScreen) built);
//$$         return built;
//$$     }
//$$
//$$     private static void getForgeConfigFields(List<Pair<ForgeConfigSpec.ConfigValue<?>, ForgeConfigSpec.ValueSpec>> values, UnmodifiableConfig config, ForgeConfigSpec spec) {
//$$         if (config == null) System.out.printf("%s, %s, %s", values, config, spec);
//$$         for (Map.Entry<String, Object> so : config.valueMap().entrySet()) {
//$$             if (so.getValue() instanceof AbstractConfig config1) {
//$$                 getForgeConfigFields(values, config1, spec);
//$$             } else if (so.getValue() instanceof ForgeConfigSpec.ConfigValue<?> value) {
//$$                 ForgeConfigSpec.ValueSpec valueSpec = spec.getRaw(value.getPath());
//$$                 values.add(new Pair<>(value, valueSpec));
//$$             }
//$$         }
//$$     }
//$$
//$$     private static Set<ModConfig> getConfigs(String id) {
//$$         HashSet<ModConfig> hashMap = new HashSet<>();
//$$         addConfigFor(hashMap, ModConfig.Type.CLIENT, id);
//$$         addConfigFor(hashMap, ModConfig.Type.COMMON, id);
//$$         addConfigFor(hashMap, ModConfig.Type.SERVER, id);
//$$         return hashMap;
//$$     }
//$$
//$$     private static void addConfigFor(HashSet<ModConfig> configMap, ModConfig.Type type, String id) {
//$$         Set<ModConfig> typeConfigs = getConfigSets().get(type);
//$$         typeConfigs.stream().filter(a -> id.equals(a.getModId()) && a.getSpec() instanceof ForgeConfigSpec).forEach(configMap::add);
//$$     }
//$$
//$$     private static EnumMap<ModConfig.Type, Set<ModConfig>> getConfigSets() {
//$$         return ObfuscationReflectionHelper.getPrivateValue(ConfigTracker.class, ConfigTracker.INSTANCE, "configSets"); // mixin on forge is unreliable
//$$     }
//$$ }
//#endif