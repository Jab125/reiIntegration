//#if LOADER<=FORGE
//$$ package com.jab125.clothintegration.forge.test;
//$$
//$$ import net.minecraft.util.Formatting;
//$$ import net.minecraftforge.common.ForgeConfigSpec;
//$$ import org.apache.commons.lang3.tuple.Pair;
//$$
//$$ import java.util.List;
//$$
//$$ public class TestConfig {
//$$
//$$     public static class Client {
//$$         public final ForgeConfigSpec.BooleanValue testBoolean;
//$$         public final ForgeConfigSpec.EnumValue<Formatting> testFormatting;
//$$         Client(ForgeConfigSpec.Builder builder) {
//$$             builder.comment("Client setinfs").push("client");
//$$             this.testBoolean = builder.comment("TESET BOOLAN").define("testBoolean", true);
//$$             this.testFormatting = builder.comment("test formatting").defineEnum("testFormatting", Formatting.RED);
//$$             builder.comment("test range").defineInRange("testRange", 0, -15, 15);
//$$             builder.comment("test list").defineList("testSetList", List.of("A", "B", "C", "D"), a -> {
//$$                 return a instanceof String b && !b.isEmpty();
//$$             });
//$$             builder.pop();
//$$         }
//$$     }
//$$
//$$     public static final Client CLIENT;
//$$
//$$     public static final ForgeConfigSpec CLIENT_SPEC;
//$$
//$$     static {
//$$         Pair<Client, ForgeConfigSpec> configure = new ForgeConfigSpec.Builder().configure(Client::new);
//$$         CLIENT = configure.getLeft();
//$$         CLIENT_SPEC = configure.getRight();
//$$     }
//$$ }
//#endif