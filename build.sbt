import Dependencies._

lazy val dissonance = project
  .in(file("."))
  .settings(commonSettings, releaseSettings, publish / skip := true)
  .aggregate(core)

lazy val core = project
  .in(file("core"))
  .settings(commonSettings, releaseSettings)
  .settings(
    name := "dissonance",
    // TODO: Remove below here when we remove Main.scala
    fork := true,                         // Fork to separate process
    connectInput := true,                 // Connects stdin to sbt during forked runs
    outputStrategy := Some(StdoutOutput), // Get rid of output prefix
    testFrameworks += new TestFramework("weaver.framework.CatsEffect")
  )

lazy val docs = project
  .in(file("dissonance-docs"))
  .settings(
    commonSettings,
    mdocIn := file("docs/README.md"),
    mdocOut := file("README.md"),
    publish / skip := true
  )
  .dependsOn(core)
  .enablePlugins(MdocPlugin)

lazy val commonSettings = Seq(
  scalaVersion := "2.13.6",
  crossScalaVersions := List(scalaVersion.value, "2.12.12"),
  scalacOptions ++= (CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) if n >= 13 => Seq("-Ymacro-annotations")
    case _                       => Nil
  }),
  libraryDependencies ++= (CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, v)) if v <= 12 => Seq(compilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full))
    case _                       => Nil
  }) ++ dependencies,
  addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1"),
  addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.11.3" cross CrossVersion.full)
)

lazy val releaseSettings = Seq(
  organization := "com.github.billzabob",
  homepage := Some(url("https://github.com/billzabob/dissonance")),
  licenses := Seq("LGPL-3.0" -> url("https://www.gnu.org/licenses/lgpl-3.0.en.html")),
  developers := List(
    Developer(
      "billzabob",
      "Nick Hallstrom",
      "cocolymoo@gmail.com",
      url("https://github.com/billzabob")
    ),
    Developer(
      "hogiyogi597",
      "Stephen Hogan",
      "stevyhogan@gmail.com",
      url("https://github.com/hogiyogi597")
    )
  )
)

Global / onChangedBuildSource := ReloadOnSourceChanges
