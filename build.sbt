name          := """kiros-search"""
version       := "1.0"
scalaVersion  := "2.11.8"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

resolvers += Resolver.jcenterRepo

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "com.typesafe.akka" %% "akka-http-experimental" % "2.4.4",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.4",
  "com.typesafe.akka" %% "akka-stream" % "2.4.4",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.4.4",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.typesafe" % "config" % "1.3.0",
  "com.iheart" %% "ficus" % "1.2.3",
  "org.elasticsearch" % "elasticsearch" % "2.3.2",
    "org.scalatest"     %% "scalatest"                         % "3.0.0-M15"       % "test",
    "org.scalamock"     %% "scalamock-scalatest-support"       % "3.2.2"       % "test",
    "org.scalaz"        %% "scalaz-scalacheck-binding"         % "7.3.0-M2"          % "test",
    "org.typelevel"     %% "scalaz-scalatest"                  % "0.3.0" % "test",
    "com.typesafe.akka" %% "akka-http-testkit"                 % "2.4.4"            % "test"
  
)
