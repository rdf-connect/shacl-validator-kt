plugins {
  kotlin("jvm") version "2.0.21"
  id("co.uzzu.dotenv.gradle") version "4.0.0"
}

group = "technology.idlab"

version = "0.0.1"

kotlin { jvmToolchain(22) }

repositories {
  mavenCentral()

  maven {
    url = uri("https://maven.pkg.github.com/rdf-connect/orchestrator")
    credentials {
      username = env.fetchOrNull("GITHUB_ACTOR") ?: System.getenv("GITHUB_ACTOR")
      password = env.fetchOrNull("GITHUB_TOKEN") ?: System.getenv("GITHUB_TOKEN")
    }
  }
}

dependencies {
  // Kotlin extensions
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

  // RDFC SDK
  implementation("technology.idlab:rdfc-core:0.0.1")
  implementation("technology.idlab:rdfc-processor:0.0.1")

  // Hide SLF4J warnings
  implementation("org.slf4j:slf4j-nop:2.0.7")

  // RDF dependencies.
  implementation("org.apache.jena:apache-jena-libs:5.0.0")
  implementation("org.apache.jena:jena-arq:5.0.0")
  implementation("org.apache.jena:jena-shacl:5.0.0")

  // KTest
  testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
  useJUnitPlatform()

  testLogging { showStandardStreams = true }
}
