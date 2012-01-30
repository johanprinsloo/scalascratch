organization := "scalanoobsinc"

name := "scalascratch"

scalaVersion := "2.9.1"

logLevel := Level.Info

parallelExecution in Test := false

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.6.1",
    "org.scala-tools" %% "scala-stm" % "0.4",
    "org.scalala" % "scalala_2.9.0" % "1.0.0.RC2-SNAPSHOT",
    "log4j" % "log4j" % "1.2.13" % "compile->default",
    "ch.qos.logback" % "logback-classic" % "0.9.30",
    "org.clapper" %% "grizzled-slf4j" % "0.6.6"
)

resolvers += "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/"

resolvers += "ondex" at "http://ondex.rothamsted.bbsrc.ac.uk/nexus/content/groups/public/"

resolvers += "ScalaNLP Maven2" at "http://repo.scalanlp.org/repo/"
