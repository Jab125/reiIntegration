package com.jab125.clothintegration.platform;

//#if LOADER<=FORGE
//$$ import net.minecraftforge.fml.ModContainer;
//$$ import net.minecraftforge.fml.ModList;
//$$ import net.minecraftforge.forgespi.language.IModInfo;
//$$ import net.minecraftforge.fml.loading.LoadingModList;
//#endif
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.jab125.clothintegration.util.ModConfig;

//#if LOADER>=FABRIC
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.CustomValue;
//#endif
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.util.Identifier;

public class PlatformUtil {

    /**
     * @implNote On forge-like platforms, forge's mod list is not fully filled out until after initialization!
     */
    public static boolean isModInstalled(String id) {
        //#if LOADER<=FORGE
        //$$ return ModList.get().isLoaded(id);
        //#else
        return FabricLoader.getInstance().isModLoaded(id);
        //#endif
    }

    public static Optional<Mod> getMod(String id) {
        if (!isModInstalled(id)) return Optional.empty();
        //#if LOADER<=FORGE
        //$$ ModContainer modContainer = ModList.get().getModContainerById(id).get();
        //$$ return Optional.of(new Mod() {
        //$$
        //$$     @Override
        //$$     public String getId() {
        //$$         return modContainer.getModId();
        //$$     }
        //$$
        //$$     @Override
        //$$     public String getName() {
        //$$         return modContainer.getModInfo().getDisplayName();
        //$$     }
        //$$
        //$$     @Override
        //$$     public String getDescription() {
        //$$         return modContainer.getModInfo().getDescription();
        //$$     }
        //$$
        //$$     @Override
        //$$     public String getVersion() {
        //$$         return modContainer.getModInfo().getVersion().toString();
        //$$     }
        //$$
        //$$     @Override
        //$$     public ModContainer backing() {
        //$$         return modContainer;
        //$$     }
        //$$ });
        //#else
        ModContainer modContainer = FabricLoader.getInstance().getModContainer(id).get();
        return Optional.of(new Mod() {

            @Override
            public String getId() {
                return modContainer.getMetadata().getId();
            }

            @Override
            public String getName() {
                return modContainer.getMetadata().getName();
            }

            @Override
            public String getDescription() {
                return modContainer.getMetadata().getDescription();
            }

            @Override
            public String getVersion() {
                return modContainer.getMetadata().getVersion().getFriendlyString();
            }

            @Override
            public ModContainer backing() {
                return modContainer;
            }
        });
        //#endif
    }

    public static Optional<Identifier> getConfiguredBackground(Mod mod) {
        if (!ModConfig.$().configuredBackgrounds) return Optional.empty();
        //#if LOADER == FABRIC
        ModContainer container = mod.backing();
        if (container.getMetadata().containsCustomValue("configured")) {
            CustomValue configured = container.getMetadata().getCustomValue("configured");
            if (configured.getType() == CustomValue.CvType.OBJECT) {
                CustomValue.CvObject configuredObject = configured.getAsObject();
                if (configuredObject.containsKey("background")) {
                    CustomValue background = configuredObject.get("background");
                    if (background.getType() == CustomValue.CvType.STRING) {
                        return Optional.ofNullable(Identifier.tryParse(background.getAsString()));
                    }
                }
            }
        }
        //#elseif LOADER <= FORGE
        //$$ Map<String, Object> modProperties = mod.backing().getModInfo().getModProperties();
        //$$ if (modProperties.containsKey("configuredBackground")) {
        //$$     if (modProperties.get("configuredBackground") instanceof String str) {
        //$$         return Optional.ofNullable(Identifier.tryParse(str));
        //$$     }
        //$$ }
        //#endif
        return Optional.empty();
    }

    public static void assertNeoForge() {
        Optional<Mod> forge = getMod(
                //#if MC == 1.20.1
                "forge"
                //#else
                //$$ "neoforge"
                //#endif
        );
        if (forge.isPresent()) {
            if ("NeoForge".equals(forge.get().getName())) {
                return;
            }
        }
        throw new IllegalStateException("This version requires NeoForge!");
    }

    public static Loader getLoader() {
        //#if LOADER==NEO
        //$$ return Loader.NEO;
        //#elseif LOADER==FORGE
        //$$ return Loader.FORGE;
        //#elseif LOADER==FABRIC
        return isModInstalled("quiltloader") ? Loader.QUILT : Loader.FABRIC;
        //#endif
    }

    //#if LOADER <= FORGE
    //$$ private static List<IModInfo> cachedForgeModList;
    //$$ // Forge has 2 mod lists.
    //$$ private static List<IModInfo> getForgeMods() {
    //$$ 	 if (cachedForgeModList == null) {
    //$$ 		 cachedForgeModList = new ArrayList<>(LoadingModList.get().getMods());
    //$$ 	 }
    //$$ 	 return cachedForgeModList;
    //$$ }
    //#endif

    public static List<Mod> getModList() {
        //#if LOADER == FABRIC
        return FabricLoader.getInstance().getAllMods().stream().map(mod -> getMod(mod.getMetadata().getId()).get()).sorted(Comparator.comparing(Mod::getId)).collect(Collectors.toList());
        //#else
        //$$ return getForgeMods().stream().map(mod -> getMod(mod.getModId()).get()).sorted(Comparator.comparing(Mod::getId)).collect(Collectors.toList());
        //#endif
    }

    public static void setBackgroundTexture(ConfigBuilder configBuilder, Identifier identifier) {
        configBuilder.setDefaultBackgroundTexture(identifier);
        //#if MC >= 1.20.5
        configBuilder.setTransparentBackground(false);
        //#endif
    }

    public static enum Loader {
        NEO(-1),
        FORGE(0),
        FABRIC(1),
        QUILT(2);

        private final int id;

        Loader(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public boolean isFabricLike() {
            return id >= 1;
        }
    }
}
