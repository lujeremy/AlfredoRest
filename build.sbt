name := "FitRest"

version := "0.1"

scalaVersion := "2.13.0-RC1"
libraryDependencies += "io.javalin" % "javalin" % "2.8.0"
libraryDependencies += "org.jetbrains.kotlin" % "kotlin-stdlib-jre8" % "1.2.71"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.26" % Test
libraryDependencies += "org.jdbi" % "jdbi" % "2.78"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.16"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.8"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.8"
libraryDependencies += "org.scala-lang" % "scala-library" % "2.13.0"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.9.8"
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-paranamer" % "2.9.8"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.8"
