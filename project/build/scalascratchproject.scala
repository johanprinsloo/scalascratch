import sbt._

class scalascratchproject(info: ProjectInfo) extends DefaultProject(info) {
  val scalaToolsSnapshots = ScalaToolsSnapshots
  val scalatest = "org.scalatest" % "scalatest" % "1.2"
  val scala_stm = "org.scala-tools" %% "scala-stm" % "0.2"
}
