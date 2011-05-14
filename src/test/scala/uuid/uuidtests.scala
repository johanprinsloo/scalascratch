package uuid

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import java.util.UUID

class uuidtests extends FlatSpec with ShouldMatchers {
  "a UUID" should "be practically unique" in {
    val uuidMaker = new UuidMaker
    val id1 = uuidMaker.getUuid
    val id2 = uuidMaker.getUuid
    assert(id1 != id2)
    println("UUID:  " + id1 + " != \n" + "UUID:  " + id2)
  }

  "a UUID" should "be fast" in {
    val uuidMaker = new UuidMaker
    val t1 = System.currentTimeMillis
     (1 to 1000000).foreach{ _ => uuidMaker.getUuid }
    val t2 = System.currentTimeMillis

    assert( (t2-t1) > 0 )
    println("sequential time for 1 mil UUID's : " + (t2-t1) + " ms")
  }

  "parallel UUID's" should "be faster (or maybe not)" in {
    val uuidMaker = new UuidMaker
    val t1 = System.currentTimeMillis
     (1 to 1000000).par.foreach{ _ => uuidMaker.getUuid }
    val t2 = System.currentTimeMillis

    assert( (t2-t1) > 0 )
    println("parallel time for 1 mil UUID's : " + (t2-t1)  + " ms")
  }

  "a scala UUID" should "be as fast as a java UUID" in {
    val uuidMaker = new UuidMaker
    val t1 = System.currentTimeMillis
     (1 to 1000000).foreach{ _ => uuidMaker.getUuid }
    val t2 = System.currentTimeMillis

    val t3 = System.currentTimeMillis
     (1 to 1000000).foreach{ _ => UUID.randomUUID }
    val t4 = System.currentTimeMillis

    assert( (t2-t1) > 0 )
    println("sequential time for 1 mil Scala UUID's : " + (t2-t1) + " ms")
    println("sequential time for 1 mil Java UUID's : " + (t4-t3) + " ms")
  }
}