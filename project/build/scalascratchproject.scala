import sbt._

class scalascratchproject(info: ProjectInfo) extends DefaultProject(info) {
  //val scalaToolsSnapshots = ScalaToolsSnapshots

  val scalaToolsSnapshots = "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/"
  val ScalaNLPMaven2 = "ScalaNLP Maven2" at "http://repo.scalanlp.org/repo/"
  val ondex = "ondex" at "http://ondex.rothamsted.bbsrc.ac.uk/nexus/content/groups/public/"

  val scalatest = "org.scalatest" %% "scalatest" % "1.6.1"
  val scala_stm = "org.scala-tools" %% "scala-stm" % "0.3"
  val scalala = "org.scalala" %% "scalala" % "1.0.0.RC2-SNAPSHOT"

  //val ejb = "javax.ejb" % "ejb" % "2.1"
  //val javaeeApi = "javax" % "javaee-api" % "6.0"
  val log4j = "log4j" % "log4j" % "1.2.13" % "compile->default"
  val logback = "ch.qos.logback" % "logback-classic" % "0.9.28"
  val grizzled = "org.clapper" %% "grizzled-slf4j" % "0.5"
}