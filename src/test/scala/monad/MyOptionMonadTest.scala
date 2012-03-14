package monad

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers


class MyOptionMonadTest extends FlatSpec with ShouldMatchers {

  val p = new MyOptionMonad

  "a Option monad " should " produce a empty option " in {
    p.computeAggregate(new MyNone())  should be an ('MyNone)
  }


  "a Option monad " should " produce a full option " in {
    val ret = p.computeAggregate( new MySome[Foo](new Foo()) )
    info(" Statistically has a 50% chance of Some: " + ret)
    ret  should be an ('MyOption)
  }
  
  "a for comprehension " should " handle proper monads correctly " in {
    p.computeYield( new MySome[Foo]( new Foo() )) should be an ('MyOption)
  }

  "our Option monads " should " produce a correct result with flatMap ops" in {

    val maybeFoo = new MySome( new Foo )

    val ret =
      maybeFoo.flatMap { foo =>
        foo.bar.flatMap { bar =>
          bar.baz.map { baz => baz.compute }
        }
      }

    info("the map and flatMap chain produced: " + ret)
    ret should  be an ('MyOption)
  }



  "our Option monads " should " Yield correct results " in {

    val maybeFoo = new MySome( new Foo )

    val ret = for {
      foo <- maybeFoo
      bar <- foo.bar
      baz <- bar.baz
    } yield baz.compute

    info("The for comprehension yielded: " + ret)
    ret should be an ('MyOption)
  }

}
