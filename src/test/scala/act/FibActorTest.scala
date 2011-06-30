package act

import act.FibActorDemo._
import org.scalatest.FunSuite
import actors.Actor

class FibActorTest extends FunSuite {

  test("orderedJoin") {
    orderedJoin ! (1, "Hello")
    orderedJoin ! (2, "world")

    Thread.sleep(200L)
    println(">>> orderedJoin State : " + orderedJoin.getState)
    assert(orderedJoin.getState === Actor.State.Terminated)

    orderedJoin.restart
    println(">>> orderedJoin State : " + orderedJoin.getState)
    // assert( orderedJoin.getState === Actor.State.Runnable )
    orderedJoin ! (1, "hello")
    orderedJoin ! (2, "again")

    Thread.sleep(200L)
    println(">>> orderedJoin State : " + orderedJoin.getState)
    assert( orderedJoin.getState === Actor.State.Terminated )
  }
}