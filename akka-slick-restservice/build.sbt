name := "akka-slick-restservice"

version := "1.0"

scalaVersion := "2.12.0"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-http-core_2.12
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core" % "10.0.2",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.2",
  "com.typesafe.akka" %% "akka-http" % "10.0.2",
  "com.typesafe.slick" %% "slick" % "3.2.0-M2",
  "mysql" % "mysql-connector-java" % "5.1.30",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0-M2",
  "org.slf4j" % "slf4j-api" % "1.7.19",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "org.flywaydb"%"flyway-core"% "3.2.1")

    