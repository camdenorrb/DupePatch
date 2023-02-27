plugins {
	java
	id("com.github.johnrengelman.shadow") version "8.0.0"
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
	compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
}
