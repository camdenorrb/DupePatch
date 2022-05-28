plugins {
	kotlin("jvm") version "1.6.21"
	id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.camdenorrb"
version = "1.1.0"

repositories {
	mavenCentral()
	maven("https://hub.spigotmc.org/nexus/content/repositories/public/") {
		name = "SpigotMC"
	}
}

dependencies {
	compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
	implementation(kotlin("stdlib-jdk8"))
}

tasks {
	shadowJar {
		relocate("org.jetbrains", "me.camdenorrb.opencast.libs.org.jetbrains")
		relocate("org.intellij", "me.camdenorrb.opencast.libs.org.intellij")
		relocate("com.google", "me.camdenorrb.opencast.libs.com.google")
		relocate("kotlin", "me.camdenorrb.opencast.libs.kotlin")
	}
}