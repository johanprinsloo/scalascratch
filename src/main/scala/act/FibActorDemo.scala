package act

import actors.Actor
import actors.Actor._

/**
 * as seen on this talk http://code.technically.us/post/7057366814/futzing-with-actors-etc-by-chris-league
 */

object FibActorDemo  {
  def fibRecur( n:Int ): Int = {
    if( n < 2 ) 1
    else fibRecur( n - 1 ) + fibRecur( n - 2 )
  }

  def fibCPS[A](n: Int, k: Int => A): A = {
    if( n < 2 ) k(1)
    else fibCPS( n - 1, ( x : Int ) =>
            fibCPS( n - 2, ( y : Int ) =>
              k(x+y)))
  }

  def fibAPS( n: Int, k: Actor ): Unit =
  if( n < 2 ) k ! 1
  else {
    val join = actor{
      react{ case x : Int =>
        react{ case y : Int => k ! (x + y) }}}

    actor{fibAPS(n-1, join)}
    fibAPS(n-2, join)
  }

  val orderedJoin = actor {
    react{ case (1, x) =>
      react{ case (2, y) => println(x, y)}}
  }
}