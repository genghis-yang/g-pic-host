val CatsVersion = "2.0.0"
val Http4sVersion = "0.21.5"
val CirceVersion = "0.13.0"
val Specs2Version = "4.10.0"
val LogbackVersion = "1.2.3"

lazy val root = (project in file("."))
  .settings(
    organization := "info.genghis",
    name := "gPicHost",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.2",
    libraryDependencies ++= Seq(
      "org.typelevel"   %% "cats-core"           % CatsVersion,
      "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s"      %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "io.circe"        %% "circe-generic"       % CirceVersion,
      "org.specs2"      %% "specs2-core"         % Specs2Version % "test",
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion,
      "org.graalvm.nativeimage" % "svm"          % "20.2.0" % Provided,
      "org.scalameta"   %% "svm-subs"            % "20.2.0"
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3"),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1")
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings",
)

graalVMNativeImageOptions ++= Seq(
  "--no-fallback",
  "--verbose",
  "-H:+ReportExceptionStackTraces",
  "-H:+TraceClassInitialization",
  "--allow-incomplete-classpath",
  "--report-unsupported-elements-at-runtime",
  "--enable-https",
  "--enable-http",
  "--enable-all-security-services",
  "--initialize-at-build-time=scala,scala.runtime.Statics"
)

enablePlugins(GraalVMNativeImagePlugin)
