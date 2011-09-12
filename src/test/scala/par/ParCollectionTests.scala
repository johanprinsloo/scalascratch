package par

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import java.util.UUID
import uuid.UuidMaker
import org.scalatest.Assertions._

class ParCollectionTests extends FlatSpec with ShouldMatchers {

  val p = new Pester

  "a Single Threaded O^2 Search " should " return a search map" in {

    val t0 = System.nanoTime()
    val ret = p.search_sinO2
    val dt = System.nanoTime() - t0

    assert( dt > 0.0 )
    info(">> measured time: " + dt/1e6 + " msec")
  }

  "a Parallel O^2 Search " should " return a search map" in {

    val t0 = System.nanoTime()
    val ret = p.search_parO2
    val dt = System.nanoTime() - t0

    assert( dt > 0.0 )
    info(">> measured time: " + dt/1e6 + " msec")
  }

  "a Single Threaded O^1 Search " should " return a search map" in {

    val t0 = System.nanoTime()
    val ret = p.search_sinO1
    val dt = System.nanoTime() - t0

    assert( dt > 0.0 )
    info(">> measured time: " + dt/1e6 + " msec")
  }
}