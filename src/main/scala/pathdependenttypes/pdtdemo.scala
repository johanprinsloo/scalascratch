package pathdependenttypes

object pdtdemo extends App {

  /**
   * from http://stackoverflow.com/questions/2693067/what-is-meant-by-scalas-path-dependent-types
   */
  case class Board(length: Int, height: Int) {
  case class Coordinate(x: Int, y: Int) {
    require(0 <= x && x < length && 0 <= y && y < height)
  }
  val occupied = scala.collection.mutable.Set[Coordinate]()
  }

  val b1 = Board(20, 20)
  val b2 = Board(30, 30)
  val c1 = b1.Coordinate(15, 15)
  val c2 = b2.Coordinate(25, 25)
  b1.occupied += c1
  b2.occupied += c2
  // Next line doesn't work
  //b1.occupied += c2   // gives a type mismatch compiler error:

  /**
        error: type mismatch;
        found   : pathdependenttypes.pdtdemo.b2.Coordinate
        required: pathdependenttypes.pdtdemo.b1.Coordinate
        b1.occupied += c2
   */
}