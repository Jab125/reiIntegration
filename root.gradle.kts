import java.util.*

plugins {
    kotlin("jvm") version "1.5.21" apply false
    id("dev.architectury.loom") version "1.2-SNAPSHOT" apply false
    id("com.jab125.preprocessor.preprocess") version "0.2.1"
    id("com.github.hierynomus.license") version "0.15.0"
    id ("architectury-plugin") version "3.4-SNAPSHOT" apply false
    id("net.minecraftforge.gradle") version "5.1.+" apply false
    id("org.spongepowered.mixin") version "0.7-SNAPSHOT" apply false
    id("com.matthewprenger.cursegradle") version "1.4.0" apply false
    id("com.modrinth.minotaur") version "2.+" apply false
    id("loom-obfuscator") version "1.0-SNAPSHOT" apply false
}


preprocess {
    val mc12001neo = createNode("1.20.1-neo", 12001, "yarn")
    val mc12001forge = createNode("1.20.1-forge", 12001, "yarn")
    val mc12001 = createNode("1.20.1-fabric", 12001, "yarn")
    val mc11904forge = createNode("1.19.4-forge", 11904, "yarn")
    val mc11904 = createNode("1.19.4-fabric", 11904, "yarn")

    mc12001neo.link(mc12001forge)
    mc12001forge.link(mc12001, file("versions/mapping-fabric-forge-1.20.1.txt"))
    mc12001.link(mc11904)
    mc11904forge.link(mc11904, file("versions/mapping-fabric-forge-1.19.4.txt"))
}
val packageJar by tasks.creating(Copy::class) {
    into("$buildDir/libs/${computeVersion()}")
}

fun computeVersion(): String {
    return rootProject.properties["version"].toString()
}

subprojects {
    buildscript {
        repositories {
            maven("https://jitpack.io")
        }
    }

    afterEvaluate {
        val projectBundleJar = project.tasks.findByName("signRemapJar")
        if (projectBundleJar != null && projectBundleJar.hasProperty("output") && project.name != "core") {
            packageJar.dependsOn(projectBundleJar)
            packageJar.from(projectBundleJar.withGroovyBuilder { getProperty("output") })
        }
    }
    project.extra["mlp"] = javaslang.Function1<String, Int> {
        val loaders = HashMap<String, Int>()
        loaders["NEO"] = -1
        loaders["FORGE"] = 0
        loaders["FABRIC"] = 1
        loaders["QUILT"] = 2

        loaders[it]
    }

    project.extra["mcp"] = javaslang.Function1<String, Int?> { string ->
        if (string == null) return@Function1 null
        val splat : List<String> = string.split(".")
        val versionStr : StringBuilder = StringBuilder()
        splat.forEach {
            versionStr.append(it.padStart(2, '0'))
        }
        versionStr.toString().toInt()
    }
}