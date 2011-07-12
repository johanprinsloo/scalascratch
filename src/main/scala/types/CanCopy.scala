package types

/**
 * from http://stackoverflow.com/questions/6647626/scala-how-to-write-method-that-returns-object-typed-to-implementation-type-of-re
 *
 */

case class Foo(f: String)

case class Bar(f: String)

trait CanCopy[A] {
  def apply(self: A, f: String): A

  def f(self: A): String
}

object CanCopy {
  implicit val fooCanCopy = new CanCopy[Foo] {
    def apply(v: Foo, f: String): Foo = v.copy(f = f)

    def f(v: Foo) = v.f
  }
  implicit val barCanCopy = new CanCopy[Bar] {
    def apply(v: Bar, f: String): Bar = v.copy(f = f)

    def f(v: Bar) = v.f
  }
  implicit val stringCanCopy = new CanCopy[String] {
    def apply(v: String, f: String): String = f

    def f(v: String) = v
  }

  def copy[A: CanCopy](v: A, f: String) = {
    val can = implicitly[CanCopy[A]]
    can(v, f)
  }

  def f[A: CanCopy](v: A) = implicitly[CanCopy[A]].f(v)
}


/***
 * separate solution
 */
trait HasBoo[T] {
  def boo(g: String): T
}

case class Boo(f: String) extends HasBoo[Boo] {
  def boo(g: String) = copy(f = g)
}

case class Bor(f: String) extends HasBoo[Bor] {
  def boo(g: String) = copy(f = g)
}
