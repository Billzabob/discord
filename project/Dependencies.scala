import sbt._

object Dependencies {

  object Versions {
    val cats            = "2.6.0"
    val catsEffect      = "3.1.1"
    val circe           = "0.13.0"
    val ciris           = "2.0.0-RC2"
    val enumeratum      = "1.6.1"
    val fs2             = "3.0.1"
    val http4s          = "1.0.0-M21"
    val newtype         = "0.4.4"
    val refined         = "0.9.24"
    val scalaTest       = "3.2.8"
    val weaverTest      = "0.7.1"
    val websocketClient = "0.5.0-M4"
  }

  object Compile {
    val cats            = "org.typelevel" %% "cats-core"              % Versions.cats
    val catsEffect      = "org.typelevel" %% "cats-effect"            % Versions.catsEffect
    val circe           = Seq("circe-core", "circe-parser", "circe-generic-extras").map("io.circe" %% _ % Versions.circe)
    val enumeratum      = Seq("enumeratum", "enumeratum-circe").map("com.beachape" %% _ % Versions.enumeratum)
    val fs2             = "co.fs2"        %% "fs2-core"               % Versions.fs2
    val http4s          = "org.http4s"    %% "http4s-circe"           % Versions.http4s
    val newtype         = "io.estatico"   %% "newtype"                % Versions.newtype
    val refined         = "eu.timepit"    %% "refined"                % Versions.refined
    val websocketClient = "org.http4s"    %% "http4s-jdk-http-client" % Versions.websocketClient
  }

  object Test {
    val Test = "test"

    val ciris      = "is.cir"              %% "ciris"       % Versions.ciris      % Test
    val scalaTest  = "org.scalatest"       %% "scalatest"   % Versions.scalaTest  % Test
    val weaverTest = "com.disneystreaming" %% "weaver-cats" % Versions.weaverTest % Test
  }

  import Compile._
  import Test._

  lazy val dependencies = Seq(
    cats,
    catsEffect,
    ciris,
    fs2,
    http4s,
    newtype,
    refined,
    scalaTest,
    weaverTest,
    websocketClient
  ) ++ enumeratum ++ circe
}
