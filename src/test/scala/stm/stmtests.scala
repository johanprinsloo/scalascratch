package org.scratch.stm

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class stmtests extends FlatSpec with ShouldMatchers {

  "a Philosopher Dinner" should "end in good time" in {
    val meals = 1000000
    for (p <- 0 until 3) {
      var elapsed = DiningPhilosophers.time(5, meals)
      printf("%3.1f usec/meal\n", (elapsed * 1000.0) / meals)
      elapsed should be > (0l)
      elapsed should be < (10000l)
    }
  }
}


