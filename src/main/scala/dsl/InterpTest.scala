package dsl

import tools.nsc.Settings
import tools.nsc.interpreter.IMain

object InterpTest extends App {

  val code = """println("Hi")"""

  val settings = new Settings
  val compilerPath = java.lang.Class.forName("scala.tools.nsc.Interpreter").getProtectionDomain.getCodeSource.getLocation
  val libPath = java.lang.Class.forName("scala.Some").getProtectionDomain.getCodeSource.getLocation

  println("compilerPath=" + compilerPath)
  println("settings.bootclasspath.value=" + settings.bootclasspath.value)

  settings.bootclasspath.value = List(settings.bootclasspath.value, compilerPath, libPath) mkString java.io.File.pathSeparator
  settings.usejavacp.value = true

  val interpreter = new IMain(settings)

  interpreter.interpret(code)
  interpreter.interpret(testCode2)
  interpreter.interpret(testFn1)

  def testCode2 =
    """
      |case class Fred(val in : String = "default") {
      |  def foo = 3
      |  println("test: " + in + " val " + foo)
      |}
    """.stripMargin

  def testFn1 =
    """
      |def testFunction( in : String = "default" ) : String = {
      |   val out = in + "run"
      |   println(out)
      |   out
      |}
    """.stripMargin
}
