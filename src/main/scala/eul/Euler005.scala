package eul
/**
 *   2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 *   What is the smallest number that is evenly divisible by all of the numbers from 1 to 20?
 *
 * http://blog.staale.org/2009/04/euler-problem-5-in-scala.html
 */

object Euler005 extends App {

  def divBy(div: Int)(x: Int) = if (x % div == 0) x / div else x

  def reduceMuls(src: List[Int]): List[Int] = src match {
    case 1 :: rest => reduceMuls(rest)
    case a :: rest => a :: reduceMuls(rest map divBy(a))
    case _ => src
  }

  println((1 /: reduceMuls(1 to 20 toList))(_ * _))
}