lazy val main = (project in file(".")).
  settings(
    name := "swing-fractal",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.11.7",
    mainClass in (Compile, run) := Some("FractalGUI"),
      resolvers ++= Seq(
          "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases",
          "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
          "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
          "MVN Repo" at "https://mvnrepository.com/artifact/"
      ),
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" % "scala-swing_2.11" % "1.0.2",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
  )
