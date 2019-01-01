/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java project to get you started.
 * For more details take a look at the Java Quickstart chapter in the Gradle
 * user guide available at https://docs.gradle.org/5.0/userguide/tutorial_java_projects.html
 */

plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building an application
    application
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
    mavenCentral()
   maven {
        setUrl("http://repo.spring.io/plugins-release/")
    }   
  evolved {
        setUrl("http://repo.evolvedbinary.com/content/repositories/exist-db/")
    }
}



dependencies {
    implementation("com.google.guava:guava:26.0-jre")
    compile (group = "org.apache.xmlbeans"     , name = "xmlbeans"    , version= "2.6.0")
    compile (group = "org.basex"               , name = "basex"       , version = "7.3.1")
    compile (group = "javax.xml.xquery"        , name = "xqj-api"     , version = "1.0")
    compile (group = "javax.xml.parsers"       , name = "jaxp-api"    , version= "1.4.5")


    // Use TestNG framework, also requires calling test.useTestNG() below
    testImplementation("org.testng:testng:6.14.3")
}

application {
    // Define the main class for the application
    mainClassName = "org.basex.examples.local.App"
}

val test by tasks.getting(Test::class) {
    // Use TestNG for unit tests
    useTestNG()
}
