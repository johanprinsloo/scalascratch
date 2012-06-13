package org.scratch.log

/**
 * http://dl.dropbox.com/u/7810909/writer-monad/chunk-html/index.html
 */

object WriterLog {
  type LOG = List[String]
}

import WriterLog._

case class Writer[A](log: LOG, a: A) {
  def map[B](f: A => B): Writer[B] =
    Writer(log, f(a))

  def flatMap[B](f: A => Writer[B]): Writer[B] = {
    val Writer(log2, b) = f(a)
    Writer(log ::: log2 /* accumulate */, b)
  }
}

object Writer {
  implicit def LogUtilities[A](a: A) = new {
    def nolog =
      Writer(Nil /* empty */, a)

    def withlog(log: String) =
      Writer(List(log), a)

    def withvaluelog(log: A => String) =
      withlog(log(a))
  }
}

object subcontract11 {
  object dummyrule11{
    val parameter11 = 23.8765
  }
}

object subcontract12 {
  object dummyrule12{
    val parameter12 = 23.8765
  }
}


object rule12 {
 val dm = "234"
}

object rule11 {
val out = subcontract11.dummyrule11.parameter11 + subcontract12.dummyrule12.parameter12
}

