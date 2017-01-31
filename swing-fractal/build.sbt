lazy val main = (project in file(".")).
  settings(
    name := "swing-fractal",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.10.6",
    mainClass in (Compile, run) := Some("FractalGUI"),
      resolvers ++= Seq(
          "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases",
          "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
          "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"
      ),
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-swing" % "2.10.6",
      "org.scalatest" % "scalatest_2.10" % "3.0.1" % "test",
      "junit" % "junit" % "4.12" % "test"
    )
  )
