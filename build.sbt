ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "web-scrapper",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client3" %% "core" % "3.10.1"
    )
  )
