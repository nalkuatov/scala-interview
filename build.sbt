lazy val scalacSettings =
  scalacOptions ++=
    Seq(
        "-encoding", "UTF-8",   // source files are in UTF-8
        "-deprecation",         // warn about use of deprecated APIs
        "-unchecked",           // warn about unchecked type parameters
        "-feature",             // warn about misused language features
        "-language:higherKinds",// allow higher kinded types without `import scala.language.higherKinds`
        "-Xlint",               // enable handy linter warnings
      //  "-Xfatal-warnings",     // turn compiler warnings into errors
        "-Ypartial-unification" // allow the compiler to unify type constructors of different arities
    )

lazy val commonSettings = scalacSettings ++ Seq(
  scalaVersion := "2.12.11",
  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)
)

lazy val `maze-runner` = project
  .in(file("maze-runner"))
  .settings(
    libraryDependencies ++= Seq(
      "org.typelevel"     %% "cats-core"           % "2.1.1",
      "org.typelevel"     %% "cats-effect"         % "2.1.3",
    )
  )
  .settings(commonSettings)

lazy val `circular-pursuit` = project
  .in(file("circular-pursuit"))
  .settings(
    libraryDependencies ++= Seq(
      "org.typelevel"     %% "cats-core"           % "2.1.1",
      "org.typelevel"     %% "cats-effect"         % "2.1.3",
      "org.scalacheck"    %% "scalacheck"          % "1.15.1" % Test
    )
  )
  .settings(commonSettings)
