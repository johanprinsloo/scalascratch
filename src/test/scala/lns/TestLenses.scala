package lns

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import lns.Lns._

class TestLenses extends FlatSpec with ShouldMatchers {

  "a Lens " should " provide cheap settter and getters to an immutable case class " in {
    val w1 = Weapon("w1", 100L)
    val s1 = Soldier( "s1", 1, w1 )

    val r1 = rankL.get( s1 )
    r1 should equal (1)

    val s2 = rankL.set(s1,2)
    s2.rank should equal (2)

    s1 should not equal (s2)

    weapL.get(s1).name should be ("w1")
    val s3 = weapL.set(s1, Weapon("w2",0L))
    s3.name should be ("s1")
    s3.rank should be (1)
    s3.weapon.name should be ("w2")
  }



}
