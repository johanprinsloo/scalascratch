import sbt._

class scalascratchproject(info: ProjectInfo) extends DefaultProject(info) {
  val scalaToolsSnapshots = ScalaToolsSnapshots
  val scalatest = "org.scalatest" % "scalatest" % "1.3"
  val scala_stm = "org.scala-tools" % "scala-stm_2.9.0.RC1" % "0.3"
  val log4j = "log4j" % "log4j" % "1.2.13" % "compile->default"
}