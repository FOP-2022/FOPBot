import org.sourcegrade.fopbot.script.FOPBotPublishPlugin

plugins {
    `java-library`
}

group = "org.sourcegrade"
version = "0.3.1-SNAPSHOT"

apply<FOPBotPublishPlugin>()

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
