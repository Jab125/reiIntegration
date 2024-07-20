package dev.jab125.reiintegration;

import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import dev.jab125.reiintegration.helper.DarkModeHelper;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

//#if LOADER <= FORGE
//$$ import net.minecraftforge.fml.common.Mod;
//$$ import net.minecraftforge.api.distmarker.Dist;
//$$ import net.minecraftforge.api.distmarker.OnlyIn;
//$$ @Mod(ReiIntegration.MOD_ID)
//#else
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
//#endif
public class ReiIntegration {
    public static final String MOD_ID = "reiintegration";
    public static Identifier id(String type, String path) {
        return new Identifier("reic", type + "/" + path);
    }

    //#if LOADER == FABRIC
    @Environment(EnvType.CLIENT)
    //#else
    //$$ @OnlyIn(Dist.CLIENT)
    //#endif
    public static void onInitializeClient() {
        System.out.println("Welcome!");
        DarkModeHelper.setup();
    }

    public ReiIntegration() {
        if (Platform.getEnvironment() == Env.CLIENT) {
            Supplier<Supplier<Runnable>> runnable = () -> () -> ReiIntegration::onInitializeClient;
            runnable.get().get().run();
        }
    }
}
