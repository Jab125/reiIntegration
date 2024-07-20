package dev.jab125.reiintegration.helper;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.registry.ReloadListenerRegistry;
import dev.jab125.reiintegration.plugin.rfm.RfmPlugin;
import me.shedaniel.clothconfig2.api.animator.NumberAnimator;
import me.shedaniel.clothconfig2.api.animator.ValueAnimator;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.gui.DrawableConsumer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class DarkModeHelper {
//    public static Identifier registerDarkModeTexture(Identifier texture) {
//
//    }

    public static DrawableConsumer rfmJei1(int x, int y, int u, int v, int width, int height) {
        final NumberAnimator<Float> darkBackgroundAlpha = ValueAnimator.ofFloat()
                .withConvention(() -> REIRuntime.getInstance().isDarkThemeEnabled() ? 1.0F : 0.0F, ValueAnimator.typicalTransitionTime())
                .asFloat();
        return (graphics, mouseX, mouseY, delta) -> {
            darkBackgroundAlpha.update(delta);
            Identifier texture = RfmPlugin.TEXTURES;
            graphics.drawTexture(texture, x, y, u, v, width, height);
            //SplashOverlay
            if (darkBackgroundAlpha.value() > 0.0F) {
                RenderSystem.setShaderColor(1, 1, 1, darkBackgroundAlpha.value());
                graphics.drawTexture(RfmPlugin.TEXTURES_DARK, x, y, u, v, width, height);
                RenderSystem.setShaderColor(1, 1, 1, 1);
            }
        };
    }

    public static DrawableConsumer rfmJei2(int x, int y, int u, int v, int width, int height) {
        final NumberAnimator<Float> darkBackgroundAlpha = ValueAnimator.ofFloat()
                .withConvention(() -> REIRuntime.getInstance().isDarkThemeEnabled() ? 1.0F : 0.0F, ValueAnimator.typicalTransitionTime())
                .asFloat();
        return (graphics, mouseX, mouseY, delta) -> {
            darkBackgroundAlpha.update(delta);
            Identifier texture = RfmPlugin.TEXTURES_2;
            graphics.drawTexture(texture, x, y, u, v, width, height);
            //SplashOverlay
            if (darkBackgroundAlpha.value() > 0.0F) {
                RenderSystem.setShaderColor(1, 1, 1, darkBackgroundAlpha.value());
                graphics.drawTexture(RfmPlugin.TEXTURES_2_DARK, x, y, u, v, width, height);
                RenderSystem.setShaderColor(1, 1, 1, 1);
            }
        };
    }

    public static DrawableConsumer rfmJei2a(int x, int y, int u, int v, int width, int height) {
        final NumberAnimator<Float> darkBackgroundAlpha = ValueAnimator.ofFloat()
                .withConvention(() -> REIRuntime.getInstance().isDarkThemeEnabled() ? 1.0F : 0.0F, ValueAnimator.typicalTransitionTime())
                .asFloat();
        return (graphics, mouseX, mouseY, delta) -> {
            darkBackgroundAlpha.update(delta);
            Identifier texture = RfmPlugin.TEXTURES_2;
            //RenderSystem.setShaderColor(1, 1, 1, 1 - darkBackgroundAlpha.value());
            graphics.drawTexture(texture, x, y, u, v, width, height);
            //RenderSystem.setShaderColor(1, 1, 1, 1);
            //SplashOverlay
            if (darkBackgroundAlpha.value() > 0.0F) {
                RenderSystem.setShaderColor(1, 1, 1, darkBackgroundAlpha.value());
                graphics.fill(x, y, width+x, height+y, 0xff3c3c3c);
                RenderSystem.setShaderColor(1, 1, 1, 1);
            }
        };
    }

    public static List<Rectangle> exclusions;

    public static List<Rectangle> JEI1_ARROW_EXCLUSIONS = List.of(
            new Rectangle(30, 9, 23, 16),
            new Rectangle(127, 0, 23, 16),
            new Rectangle(203, 19, 23, 16),
            new Rectangle(93, 0, 23, 16),
            new Rectangle(93, 154, 23, 16),
            new Rectangle(93, 173, 23, 16),
            new Rectangle(60, 49, 9, 9),
            new Rectangle(91, 49, 15, 11),
            new Rectangle(191, 63, 13, 12),
            new Rectangle(203, 88, 24, 17),
            new Rectangle(59, 82, 13, 13),
            //new Rectangle(73, 105, 28, 23),
            new Rectangle(29, 165, 26, 15),
            new Rectangle(28, 211, 26, 20),
            new Rectangle(132, 136, 27, 18),
            new Rectangle(203, 188, 16, 23),
            new Rectangle(216, 229, 13, 9),
            new Rectangle(71, 106, 25, 20)
    );

    public static List<Rectangle> JEI2_ARROW_EXCLUSIONS = List.of(
            new Rectangle(65, 22, 25, 19),
            new Rectangle(160, 0, 17, 16),
            new Rectangle(234, 24, 16, 14),
            new Rectangle(23, 183, 16, 14),
            new Rectangle(54, 219, 10, 10),
            new Rectangle(82, 220, 12, 8)
    );

    public static List<Rectangle> JEI2_FAN = List.of(
            new Rectangle(120, 0, 42, 124)
    );

    public static List<Rectangle> JEI2_DEFINITELY_EXCLUDE = List.of(
            new Rectangle(18, 208, 25, 19)
    );

    public static Boolean fanExcluding = null;

    public static void setup() {
        final Identifier id = new Identifier("fhdfsh");
        ReloadListenerRegistry.register(ResourceType.CLIENT_RESOURCES, new SimpleResourceReloadListener<>() {

            //#if LOADER == FABRIC
            @Override
            public Identifier getFabricId() {
                return id;
            }
            //#endif

            @Override
            public CompletableFuture<Object> load(ResourceManager manager, Profiler profiler, Executor executor) {
                try {
                    NativeImage nativeImage = getNativeImage(manager, RfmPlugin.TEXTURES, RfmPlugin.TEXTURES_DARK, JEI1_ARROW_EXCLUSIONS);
                    NativeImage nativeImage1 = getNativeImage(manager, RfmPlugin.TEXTURES_2, RfmPlugin.TEXTURES_2_DARK, JEI2_ARROW_EXCLUSIONS);

                    Files.write(Path.of("testing2.png"), nativeImage.getBytes());
                    Files.write(Path.of("testing3.png"), nativeImage1.getBytes());
                    //bytes = inputStream.readAllBytes();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return CompletableFuture.completedFuture(new Object());
            }

            private static @NotNull NativeImage getNativeImage(ResourceManager manager, Identifier in, Identifier out, List<Rectangle> exclusions) throws IOException {
                DarkModeHelper.exclusions = exclusions;
                InputStream inputStream = manager.getResource(in).get().getInputStream();
                NativeImage read = NativeImage.read(inputStream);
                NativeImage nativeImage = read; // 0b0b0b

                fanExcluding = null;
                fanExcluding = false;
                replaceColors(nativeImage, 0xff8b8b8b, 0xff2e2e2e, true);
                fanExcluding = null;
                replaceColors(nativeImage, 0xffc6c6c6, 0xff2e2e2e, null);
                fanExcluding = false;
                replaceColors(nativeImage, 0xff8b8b8b, 0xff555555, true);
                replaceColors(nativeImage, 0xffffffff, 0xff626262, true);
                fanExcluding = null;
                // replaceColors(nativeImage, 0xff626262, 0xff2e2e2e);
                replaceColors(nativeImage,  0xff373737, 0xff0b0b0b, true);

                replaceColors(nativeImage,0xffc3c3c3, 0xff333333, null);
                replaceColors(nativeImage,0xffc4c4c4, 0xff333333, null);
                replaceColors(nativeImage,0xffbdbdbd, 0xff363636, null);
                replaceColors(nativeImage,0xffbebebe, 0xff363636, null);
                replaceColors(nativeImage,0xffbfbfbf, 0xff363636, null);
                replaceColors(nativeImage,0xffb5b5b5, 0xff3c3c3c, null);
                replaceColors(nativeImage,0xffb4b4b4, 0xff3c3c3c, null);
                replaceColors(nativeImage,0xffb3b3b3, 0xff3c3c3c, null);
                replaceColors(nativeImage,0xffb2b2b2, 0xff3c3c3c, null);

                // i bet this doesn't work
                replaceColors(nativeImage,0xffadadad, 0xff3c3c3c, null);
                replaceColors(nativeImage,0xffAEAEAE, 0xff3c3c3c, null);
                replaceColors(nativeImage,0xffafafaf, 0xff3c3c3c, null);


                // normal arrow color
                replaceColors(nativeImage, 0xff8b8b8b, 0xff555555, false);
                fanExcluding = false;
                replaceColors(nativeImage, 0xff686868, 0xff0b0b0b, false);

                fanExcluding = true;
                replaceColors(nativeImage, 0xffffffff, 0xff2e2e2e, true);
                replaceColors(nativeImage, 0xff686868, 0xff0b0b0b, true);
                fanExcluding = null;
                MinecraftClient.getInstance().getTextureManager().registerTexture(out, new NativeImageBackedTexture(nativeImage));
                return nativeImage;
            }

            @Override
            public CompletableFuture<Void> apply(Object data, ResourceManager manager, Profiler profiler, Executor executor) {

                return CompletableFuture.completedFuture(null);
            }
        }, id);
    }

    public static NativeImage replaceColors(NativeImage original, int color1, int color2, Boolean exclusions) {
        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                int finalX = x;
                int finalY = y;
                //if (fanExcluding == null && DarkModeHelper.exclusions == JEI2_ARROW_EXCLUSIONS && DarkModeHelper.JEI2_FAN.stream().anyMatch(a -> a.contains(finalX, finalY))) continue;
                if (fanExcluding == Boolean.FALSE && DarkModeHelper.exclusions == JEI2_ARROW_EXCLUSIONS && DarkModeHelper.JEI2_FAN.stream().anyMatch(a -> a.contains(finalX, finalY))) continue;
                if (fanExcluding == Boolean.TRUE && DarkModeHelper.exclusions == JEI2_ARROW_EXCLUSIONS && DarkModeHelper.JEI2_FAN.stream().noneMatch(a -> a.contains(finalX, finalY))) continue;
                if (DarkModeHelper.exclusions == JEI2_ARROW_EXCLUSIONS && DarkModeHelper.JEI2_DEFINITELY_EXCLUDE.stream().anyMatch(a -> a.contains(finalX, finalY))) continue;
                if (exclusions == Boolean.TRUE && DarkModeHelper.exclusions.stream().anyMatch(a -> a.contains(finalX, finalY))) continue;
                if (exclusions == Boolean.FALSE && DarkModeHelper.exclusions.stream().noneMatch(a -> a.contains(finalX, finalY))) continue;
                if (original.getColor(x, y) == color1) {
                    original.setColor(x, y, color2);
                } else {
                    //System.out.println(Integer.toHexString(original.getColor(x, y)));
                }
            }
        }
        return original;
    }
}
