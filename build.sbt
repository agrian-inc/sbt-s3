name := "sbts3"

description := "S3 Plugin for sbt"

version := "0.10.4-SNAPSHOT"

isSnapshot := true

organization := "cf.janga"

organizationName := "Janga"

startYear := Some(2013)

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)

libraryDependencies ++= Seq("com.amazonaws" % "aws-java-sdk-s3" % "1.11.427",
                            "commons-lang" % "commons-lang" % "2.6",
                            "javax.xml.bind" % "jaxb-api" % "2.2.11",
                            "com.sun.xml.bind" % "jaxb-core" % "2.2.11",
                            "com.sun.xml.bind" % "jaxb-impl" % "2.2.11",
                            "javax.activation" % "activation" % "1.1.1")

scalacOptions in (Compile, doc) ++=
  Opts.doc.title(name.value + ": " + description.value) ++
  Opts.doc.version(version.value) ++
  Seq("-doc-root-content", (sourceDirectory.value / "main/rootdoc.txt").getAbsolutePath())

publishMavenStyle := false

crossSbtVersions := Seq("0.13.18", "1.2.8")


licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0"))

publishTo := {
  val artifactory = "https://artifactory.agrian.com/artifactory"
  if (isSnapshot.value) {
    Some(
      "snapshots"
        .at(s"$artifactory/sbt-dev-local;build.timestamp=" + new java.util.Date().getTime)
    )
  } else {
    Some("releases".at(s"$artifactory/sbt-release-local"))
  }
}