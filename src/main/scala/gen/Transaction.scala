package gen

/**
 * http://stackoverflow.com/questions/6002418/scala-implementing-javas-aspectj-around-advice-or-python-decorators
 */

object Transaction {
  def transaction(f: => Unit) = {
    println("start transaction")
    try {
      f
      println("end successful transaction")
    } catch {
      case ex =>
        println("Failed transaction with " + ex)
        // rollback?
        println("end failed transaction")
    } finally {
      // cleanup?
      println("end cleanup")
    }
  }
}