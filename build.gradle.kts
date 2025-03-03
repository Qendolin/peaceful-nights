@file:Suppress("UnstableApiUsage")

import java.util.Properties

plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
    id("me.modmuss50.mod-publish-plugin")
    id("com.github.johnrengelman.shadow")
}

val minecraft = stonecutter.current.version
val loader = loom.platform.get().name.lowercase()

version = "${mod.version}+$minecraft"
group = mod.group
base {
    archivesName.set("${mod.id}-$loader")
}

architectury.common(stonecutter.tree.branches.mapNotNull {
    if (stonecutter.current.project !in it) null
    else it.prop("loom.platform")
})
repositories {
    maven("https://maven.neoforged.net/releases/")
    maven("https://maven.parchmentmc.org")

    //modmenu
    maven("https://maven.terraformersmc.com/")
    //placeholder api (modmenu depencency)
    maven("https://maven.nucleoid.xyz/")

    maven {
        name = "Modrinth"
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroup("maven.modrinth")
        }
    }

}


loom {
    silentMojangMappingsLicense()

    accessWidenerPath = rootProject.file("src/main/resources/peaceful_night.accesswidener")

    decompilers {
        get("vineflower").apply { // Adds names to lambdas - useful for mixins
            options.put("mark-corresponding-synthetics", "1")
        }
    }
    if (loader == "forge") {
        forge.mixinConfigs(
            "peaceful_night-common.mixins.json",
            "peaceful_night-forge.mixins.json",
        )
    }

    runConfigs.configureEach {
        runDir = "../../run"
    }
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraft")

    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-$minecraft:${mod.dep("parchment_build")}@zip")
    })

    if (loader == "fabric") {
        modImplementation("net.fabricmc:fabric-loader:${mod.dep("fabric_loader")}")

        modImplementation("net.fabricmc.fabric-api:fabric-api:${mod.dep("fabric_version")}")
        modImplementation("com.terraformersmc:modmenu:${mod.dep("modmenu_version")}")
    }
    if (loader == "forge") {
        "forge"("net.minecraftforge:forge:${minecraft}-${mod.dep("forge_loader")}")

        "io.github.llamalad7:mixinextras-common:${mod.dep("mixin_extras")}".let {
            annotationProcessor(it)
            compileOnly(it)
        }
        "io.github.llamalad7:mixinextras-forge:${mod.dep("mixin_extras")}".let {
            implementation(it)
            include(it)
        }
    }
    if (loader == "neoforge") {
        "neoForge"("net.neoforged:neoforge:${mod.dep("neoforge_loader")}")
    }

    modCompileOnly("maven.modrinth:enhanced-celestials:${mod.dep("enhanced_celestials")}")
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}
publishMods {
    val modrinthToken = localProperties.getProperty("publish.modrinthToken", "")
    val curseforgeToken = localProperties.getProperty("publish.curseforgeToken", "")


    file = project.tasks.remapJar.get().archiveFile
    dryRun = modrinthToken == null || curseforgeToken == null

    displayName = "${mod.name} ${loader.replaceFirstChar { it.uppercase() }} ${property("mod.mc_title")}-${mod.version}"
    version = mod.version
    type = BETA

    modLoaders.add(loader)

    val targets = property("mod.mc_targets").toString().split(' ')
    modrinth {
        projectId = property("publish.modrinth").toString()
        accessToken = modrinthToken
        targets.forEach(minecraftVersions::add)
        if (loader == "fabric") {
            requires("fabric-api")
        }
    }

//    curseforge {
//        projectId = property("publish.curseforge").toString()
//        accessToken = curseforgeToken.toString()
//        targets.forEach(minecraftVersions::add)
//        if (loader == "fabric") {
//            requires("fabric-api")
//            optional("modmenu")
//        }
//    }
}

java {
    withSourcesJar()
    val java = if (stonecutter.eval(minecraft, ">=1.20.5")) JavaVersion.VERSION_21 else JavaVersion.VERSION_17
    targetCompatibility = java
    sourceCompatibility = java
}

val shadowBundle: Configuration by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

tasks.shadowJar {
    configurations = listOf(shadowBundle)
    archiveClassifier = "dev-shadow"
}

tasks.remapJar {
    injectAccessWidener = true
    input = tasks.shadowJar.get().archiveFile
    archiveClassifier = null
    dependsOn(tasks.shadowJar)
}

tasks.jar {
    archiveClassifier = "dev"
}

val buildAndCollect = tasks.register<Copy>("buildAndCollect") {
    group = "versioned"
    description = "Must run through 'chiseledBuild'"
    from(tasks.remapJar.get().archiveFile, tasks.remapSourcesJar.get().archiveFile)
    into(rootProject.layout.buildDirectory.file("libs/${mod.version}/$loader"))
    dependsOn("build")
}

if (stonecutter.current.isActive) {
    rootProject.tasks.register("buildActive") {
        group = "project"
        dependsOn(buildAndCollect)
    }

    rootProject.tasks.register("runActive") {
        group = "project"
        dependsOn(tasks.named("runClient"))
    }

    rootProject.tasks.register("genSourcesActive") {
        group = "project"
        dependsOn(tasks.named("genSources"))
    }
}

tasks.processResources {
    properties(
        listOf("fabric.mod.json"),
        "id" to mod.id,
        "name" to mod.name,
        "version" to mod.version,
        "minecraft" to mod.prop("mc_dep_fabric")
    )
    properties(
        listOf("META-INF/mods.toml", "pack.mcmeta"),
        "id" to mod.id,
        "name" to mod.name,
        "version" to mod.version,
        "minecraft" to mod.prop("mc_dep_forgelike")
    )
    properties(
        listOf("META-INF/neoforge.mods.toml", "pack.mcmeta"),
        "id" to mod.id,
        "name" to mod.name,
        "version" to mod.version,
        "minecraft" to mod.prop("mc_dep_forgelike")
    )
}

tasks.build {
    group = "versioned"
    description = "Must run through 'chiseledBuild'"
}
