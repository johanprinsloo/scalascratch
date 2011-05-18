package aop

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import gen.Transaction

class aoptest extends FlatSpec with ShouldMatchers {

  "a functional transaction" should "be executed sequentially" in {
    val aspect = new Aspect("execution(* *.bar(..))")
      with InterceptorAround
      with TransactionInterceptor

    val foo = aspect.create[Foo](new FooImpl)

    foo.foo("foo")
    foo.bar("bar")

    assert(true)

  }

}