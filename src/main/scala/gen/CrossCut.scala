package gen

import reflect.Method
import javax.ejb.{TransactionAttribute, TransactionAttributeType}

/**
 * http://java.dzone.com/articles/real-world-scala-managing-cros
 */

//mixin based cross cutting
class Human(name: String) {
 def sayHello = println("hello")
}

trait Dog {
  def greet(me: Human)
  def doStuff(me: Human) = println("dog dostuff " + me)
}

trait DogMood extends Dog {
  def greet(me: Human) {
    println(me.sayHello)
  }
}


trait AngryDog extends DogMood {
  abstract override def greet(me: Human) {
    println("Dog: Barks @ " + me)
    super.doStuff(me)
  }
}

trait HungryDog extends DogMood {
  abstract override def greet(me: Human) {
    super.doStuff(me)
    println("Dog: Bites " + me)
  }
}

//-----------------------------------------------------------------------------
/**
 * Generic pointcut-based aspects
 */
//case class Invocation(val method: Method, val args: Array[AnyRef], val target: AnyRef) {
//  def invoke: AnyRef = method.invoke(target, args:_*)
//  override def toString: String = "Invocation [method: " + method.getName + ", args: " + args + ", target: " + target + "]"
//  override def hashCode(): Int = { 100 }
//  override def equals(that: Any): Boolean = { true }
//}
//
//
//trait Interceptor {
//  protected val parser = PointcutParser.getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution
//
//  protected def matches(pointcut: PointcutExpression, invocation: Invocation): Boolean = {
//    pointcut.matchesMethodExecution(invocation.method).alwaysMatches ||
//    invocation.target.getClass.getDeclaredMethods.exists(pointcut.matchesMethodExecution(_).alwaysMatches) ||
//    false
//  }
//
//  protected def matches(annotationClass: Class[T] forSome {type T <: Annotation}, invocation: Invocation): Boolean = {
//    invocation.method.isAnnotationPresent(annotationClass) ||
//    invocation.target.getClass.isAnnotationPresent(annotationClass) ||
//    false
//  }
//
//  def invoke(invocation: Invocation): AnyRef
//}
//
//object ManagedComponentFactory {
//  def createComponent[T](intf: Class[T] forSome {type T}, proxy: ManagedComponentProxy): T =
//    Proxy.newProxyInstance(
//      proxy.target.getClass.getClassLoader,
//      Array(intf),
//      proxy).asInstanceOf[T]
//}
//
//class ManagedComponentProxy(val target: AnyRef) extends InvocationHandler {
//  def invoke(proxy: AnyRef, m: Method, args: Array[AnyRef]): AnyRef = invoke(Invocation(m, args, target))
//  def invoke(invocation: Invocation): AnyRef = invocation.invoke
//}
//
//
//
//trait Foo {
//  @TransactionAttribute(TransactionAttributeType.REQUIRED)
//  def foo(msg: String)
//  def bar(msg: String)
//}
//
//class FooImpl extends Foo {
//  val bar: Bar = new BarImpl
//  def foo(msg: String) = println("msg: " + msg)
//  def bar(msg: String) = bar.bar(msg)
//}
//
//trait Bar {
//  def bar(msg: String)
//}
//
//class BarImpl extends Bar {
//  def bar(msg: String) = println("msg: " + msg)
//}
//
//trait LoggingInterceptor extends Interceptor {
//  val loggingPointcut = parser.parsePointcutExpression("execution(* *.foo(..))")
//
//  abstract override def invoke(invocation: Invocation): AnyRef =
//    if (matches(loggingPointcut , invocation)) {
//      println("=====> Enter: " + invocation.method.getName + " @ " + invocation.target.getClass.getName)
//      val result = super.invoke(invocation)
//      println("=====> Exit: " + invocation.method.getName + " @ " + invocation.target.getClass.getName)
//      result
//    } else super.invoke(invocation)
//}
//
//trait TransactionInterceptor extends Interceptor {
//  val matchingJtaAnnotation = classOf[javax.ejb.TransactionAttribute]
//
//  abstract override def invoke(invocation: Invocation): AnyRef =
//    if (matches(matchingJtaAnnotation, invocation)) {
//      println("=====> TX begin")
//      try {
//        val result = super.doStuff
//        println("=====> TX commit")
//        result
//      } catch {
//        case e: Exception =>
//          println("=====> TX rollback ")
//      }
//    } else super.invoke(invocation)
//}

class CrossCut extends App {
 //mixin dog
  val dog = new Dog with AngryDog with HungryDog
   dog.greet(new Human("Me"))


//  var foo = ManagedComponentFactory.createComponent[Foo](
//      classOf[Foo],
//      new ManagedComponentProxy(new FooImpl)
//        with LoggingInterceptor
//        with TransactionInterceptor)
//
//    foo.foo("foo")
//    foo.bar("bar")

}