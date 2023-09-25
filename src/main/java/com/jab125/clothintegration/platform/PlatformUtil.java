package com.jab125.clothintegration.platform;

//#if LOADER<=FORGE
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
//#endif
import java.util.Optional;

//#if LOADER>=FABRIC
//$$ import net.fabricmc.loader.api.FabricLoader;
//$$ import net.fabricmc.loader.api.ModContainer;
//#endif

public class PlatformUtil {

    /**
     * @implNote On forge-like platforms, forge's mod list is not fully filled out until after initialization!
     */
    public static boolean isModInstalled(String id) {
        //#if LOADER<=FORGE
        return ModList.get().isLoaded(id);
        //#else
        //$$ return FabricLoader.getInstance().isModLoaded(id);
        //#endif
    }

    public static Optional<Mod> getMod(String id) {
        if (!isModInstalled(id)) return Optional.empty();
        //#if LOADER<=FORGE
        ModContainer modContainer = ModList.get().getModContainerById(id).get();
        return Optional.of(new Mod() {

            @Override
            public String getId() {
                return modContainer.getModId();
            }

            @Override
            public String getName() {
                return modContainer.getModInfo().getDisplayName();
            }

            @Override
            public String getDescription() {
                return modContainer.getModInfo().getDescription();
            }

            @Override
            public String getVersion() {
                return modContainer.getModInfo().getVersion().toString();
            }
        });
        //#else
        //$$ ModContainer modContainer = FabricLoader.getInstance().getModContainer(id).get();
        //$$ return Optional.of(new Mod() {
        //$$
        //$$     @Override
        //$$     public String getId() {
        //$$         return modContainer.getMetadata().getId();
        //$$     }
        //$$
        //$$     @Override
        //$$     public String getName() {
        //$$         return modContainer.getMetadata().getName();
        //$$     }
        //$$
        //$$     @Override
        //$$     public String getDescription() {
        //$$         return modContainer.getMetadata().getDescription();
        //$$     }
        //$$
        //$$     @Override
        //$$     public String getVersion() {
        //$$         return modContainer.getMetadata().getVersion().getFriendlyString();
        //$$     }
        //$$ });
        //#endif
    }

    public static void assertNeoForge() {
        Optional<Mod> forge = getMod("forge");
        if (forge.isPresent()) {
            if ("NeoForge".equals(forge.get().getName())) {
                return;
            }
        }
        throw new IllegalStateException("This version requires NeoForge!");
    }

    public static Loader getLoader() {
        //#if LOADER==NEO
        return Loader.NEO;
        //#elseif LOADER==FORGE
        //$$ return Loader.FORGE;
        //#elseif LOADER==FABRIC
        //$$ return isModInstalled("quiltloader") ? Loader.QUILT : Loader.FABRIC;
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
