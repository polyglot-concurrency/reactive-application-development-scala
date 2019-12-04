name := "Guidebook_Demo"

version := "1.0"

val akkaVersion = "2.6.0"

scalaVersion := "2.13.1"

resolvers += "Lightbend Repository" at "https://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
)

//scalacOptions += "-Wunused"
scalacOptions += "-Ywarn-unused"
