organization := "scalanoobsinc"

name := "scalascratch"

scalaVersion := "2.10.0"

logLevel := Level.Info

parallelExecution in Test := false

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.9.1",
    "org.scala-stm" %% "scala-stm" % "0.8-SNAPSHOT",
    "org.scala-lang" % "scala-compiler" % "2.10.0",
    "com.typesafe.akka" % "akka-actor" % "2.0.2",
    //"com.googlecode.scalascriptengine" % "scalascriptengine" % "1.2.0",
    "org.scalanlp" %% "breeze-core" % "0.2-SNAPSHOT",
    "org.scalanlp" %% "breeze-viz" % "0.2-SNAPSHOT",
    "log4j" % "log4j" % "1.2.13" % "compile->default",
    "ch.qos.logback" % "logback-classic" % "0.9.30",
    "org.clapper" %% "grizzled-slf4j" % "1.0.1"
)

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/"

resolvers += "Scala Tools releases" at "http://scala-tools.org/repo-releases"

resolvers += "ondex" at "http://ondex.rothamsted.bbsrc.ac.uk/nexus/content/groups/public/"

resolvers += "ScalaNLP Maven2" at "http://repo.scalanlp.org/repo/"

