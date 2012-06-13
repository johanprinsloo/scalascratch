package monad

import util.Random

/**
 * play with http://marakana.com/static/courseware/scala/presentation/comprehending-monads.html
 */

class Foo {
  def bar: MyOption[Bar] = {
    new Random(System.currentTimeMillis()).nextInt(100) match {
      case  x if 0 to 50 contains x => new MySome(new Bar)
      case  x if 51 to 100 contains x => new MyNone
    }
  }
}

class Bar {
  def baz: MyOption[Baz]  = {
    new Random(System.currentTimeMillis()).nextInt(100) match {
      case  x if 0 to 50 contains x => new MySome(new Baz)
      case  x if 51 to 100 contains x => new MyNone
    }
  }
}

class Baz {
  def compute: Int = new Random(System.currentTimeMillis()).nextInt(100)
}

trait Monad[A] {
  def map[B](f: A => B): Monad[B]
  def flatMap[B](f: A => Monad[B]): Monad[B]
}

sealed trait MyOption[A] {
  def map[B](f: A => B): MyOption[B]
  def flatMap[B](f: A => MyOption[B]): MyOption[B]
  def isMyOption = true
}

case class MySome[A](a: A) extends MyOption[A] {
  def map[B](f: A => B): MyOption[B] = new MySome(f(a))
  def flatMap[B](f: A => MyOption[B]): MyOption[B] = f(a)
  def isMySome = true
}

case class MyNone[A] extends MyOption[A] {
  def map[B](f: A => B): MyOption[B] = new MyNone
  def flatMap[B](f: A => MyOption[B]): MyOption[B] = new MyNone
  def isMyNone = true
}

class MyOptionMonad {

  def computeBaz(baz: Baz): Int =  baz.compute

  def computeBar(bar: Bar): MyOption[Int] = bar.baz.map { computeBaz }

  def computeFoo(foo: Foo): MyOption[Int] = foo.bar.flatMap { computeBar }

  def compute(maybeFoo: MyOption[Foo]): MyOption[Int] = maybeFoo.flatMap { computeFoo }

  def computeAggregate(maybeFoo: MyOption[Foo]): MyOption[Int] =
    maybeFoo.flatMap { foo =>
      foo.bar.flatMap { bar =>
        bar.baz.map { baz =>
          baz.compute
        }
      }
    }

  def computeYield(maybeFoo: MyOption[Foo]): MyOption[Int] =
    for {
      foo <- maybeFoo
      bar <- foo.bar
      baz <- bar.baz
    } yield baz.compute

}


