package org.scratch.fnc


object curryFn   {
  def cat(s1: String)(s2: String) = s1 + "cat " +  s2 + "cat"
  println( cat("bob")("fluffy") )


  def uncurriedCat(s1: String, s2: String)  = s1 + "cat " +  s2 + "cat"
  def curriedCat = (uncurriedCat( _ , _ )).curried

  val acat = curriedCat( "wild" )( _ )
  println( acat( "spotted " ) )


}