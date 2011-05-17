package log

import org.scalatest.FunSuite

class griztest extends FunSuite {
  test("Member logging"){
    val testee = new grizzledlog
    testee.one
    testee.two
    testee.three
    testee.err

  }

  test("Trait logging"){
    val testee = new grizzledlogT
    testee.one
    testee.two
    testee.three
    testee.err
  }

}