package aop

import java.lang.reflect.{Proxy, InvocationHandler, Method}
import java.lang.annotation.Annotation
import org.aspectj.weaver.tools.{PointcutParser, PointcutExpression}
import reflect.Method

class Aspect(val pointcutExpression: String) extends Interceptor {
  def create[I: ClassManifest](target: AnyRef): I = {

    val interface = implicitly[ClassManifest[I]]

    val proxy = new TargetProxy(this, target)

    Proxy.newProxyInstance(
      target.getClass.getClassLoader,
      Array(interface.erasure),
      proxy).asInstanceOf[I]
  }

  def invoke(invocation: Invocation): AnyRef = invocation.invoke

  val pointcut = parser.parsePointcutExpression(pointcutExpression)
}

class TargetProxy(val aspect: Aspect, val target: AnyRef) extends InvocationHandler {
  def invoke(proxy: AnyRef, m: Method, args: Array[AnyRef]): AnyRef = aspect.invoke(Invocation(m, args, target))
}

trait Interceptor {
  protected val parser = PointcutParser.getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution

  protected def matches(pointcut: PointcutExpression, invocation: Invocation): Boolean = {
    pointcut.matchesMethodExecution(invocation.method).alwaysMatches ||
            //invocation.target.getClass.getDeclaredMethods.exists(pointcut.matchesMethodExecution(_).alwaysMatches) ||
            false
  }

  protected def matches[T <: Annotation](annotationClass: Class[T], invocation: Invocation): Boolean = {

    invocation.method.isAnnotationPresent(annotationClass) ||
            invocation.target.getClass.isAnnotationPresent(annotationClass) ||
            false
  }

  def invoke(invocation: Invocation): AnyRef

  val pointcut: PointcutExpression
}


abstract trait InterceptorInvoker extends Interceptor {

  def before: AnyRef = null
  def after(result: AnyRef): AnyRef = result
  def around(invoke: => AnyRef): AnyRef = invoke

  abstract override def invoke(invocation: Invocation): AnyRef =
    if (matches(pointcut, invocation)) {
      before
      val result = around(super.invoke(invocation))
      after(result)
    } else
      super.invoke(invocation)
}

trait BeforeInterceptor extends InterceptorInvoker {
  def before: AnyRef
}

trait AfterInterceptor extends InterceptorInvoker {
  def after(result: AnyRef): AnyRef
}

trait AroundInterceptor extends InterceptorInvoker {
  def around(invoke: => AnyRef): AnyRef
}

case class Invocation(val method: Method, val args: Array[AnyRef], val target: AnyRef) {
  def invoke: AnyRef = method.invoke(target, args: _*)

  override def toString: String = "Invocation [method: " + method.getName + ", args: " + args + ", target: " + target + "]"

  override def hashCode(): Int = method.hashCode
  //override def equals (that: Any): Boolean = {...}
}

class aop extends App {

}