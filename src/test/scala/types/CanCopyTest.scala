package types

import org.scalatest.FunSuite

class CanCopyTest extends FunSuite {

  test("cancopy quick test"){
    println( CanCopy.copy(Foo("foo"), "something") )
    println( CanCopy.f(Foo("foo"))  )
    println( CanCopy.copy(Bar("bar"), "something") )
    println( CanCopy.copy("string", "something") )
  }

  test("has boo test"){

    val borb = Bor("a").boo("b")
    val boob = Boo("a").boo("b")
    println( borb )
    println( boob )
  }
}