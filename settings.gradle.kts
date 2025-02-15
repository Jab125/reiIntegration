pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://jitpack.io")
        maven ("https://maven.fabricmc.net")
        maven ("https://maven.architectury.dev")
        mavenLocal()
        maven ("https://maven.neoforged.net/releases")
        maven ("https://maven.minecraftforge.net/")
        maven("https://maven.quiltmc.org/")
        maven("https://repo.essential.gg/repository/maven-public")
        maven("https://repo.spongepowered.org/maven")
        maven("https://maven.jab125.dev/")
    }
    resolutionStrategy {
        eachPlugin {
            when(requested.id.id) {
                "com.replaymod.preprocess" -> {
                    useModule("com.github.replaymod:preprocessor:${requested.version}")
                }
                "com.jab125.preprocessor.preprocess" -> {
                    useModule("com.jab125.preprocessor:preprocessor:${requested.version}")
                }
                "org.spongepowered.mixin" -> {
                    useModule("org.spongepowered:mixingradle:0.7-SNAPSHOT")
                }
            }
        }
    }
}

rootProject.name = "reiIntegration"
rootProject.buildFileName = "root.gradle.kts"

val modVersions = listOf(
        //   "1.19",
        //    "1.19.1",
        "1.20.1-fabric", "1.20.1-forge"//, "1.20.4-fabric", "1.20.4-forge", "1.20.4-neo"
)


modVersions.forEach { version ->
    include(":$version")
    project(":$version").apply {
        projectDir = file("versions/$version")
        buildFileName = "../../build.gradle"
    }
}