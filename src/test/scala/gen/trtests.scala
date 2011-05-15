package gen

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class trtests extends FlatSpec with ShouldMatchers {

  "a functional transaction" should "be executed sequentially" in {

    Transaction.transaction {
      println("..inside transaction")
    }
    assert(true)

  }

  "a functional transaction" should "fail gracefully" in {

    Transaction.transaction {
      println("..inside bad transaction")
      100/0
    }
    assert(true)

  }

}