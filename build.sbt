ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"

val springVersion = "6.2.6"
val jettyVersion = "12.0.19"

lazy val root = (project in file("."))
  .settings(
    name := "scala-servlet",
    idePackagePrefix := Some("com.andy"),
    libraryDependencies ++= Seq(
      "org.eclipse.jetty" % "jetty-server" % jettyVersion,
      "org.eclipse.jetty.ee10" % "jetty-ee10-servlet" % jettyVersion,

      "org.springframework" % "spring-core" % springVersion,
      "org.springframework" % "spring-context" % springVersion,
      "org.springframework" % "spring-webmvc" % springVersion,
    )
  )
