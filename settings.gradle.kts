plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
rootProject.name = "dk5"

dependencyResolutionManagement {
  versionCatalogs {
    create("libs") {
      from(files("gradle/libs.version.toml"))
    }
  }
}