package org.scratch.act

import actors.Actor

class ExceptionalActor extends Actor{

  def act() {
    loop {
      react {
        case "bad" => throw new NoSuchFieldException("Bad Message")
        case "impossible" => throw new Exception("Impossible Exception")
        case m => println("non-bad message " + m )
      }
    }
  }

  override def exceptionHandler = {
    case e: NoSuchFieldException => println("handled " + e.getMessage() )
  }
}

object Tester extends App {
  val eActr = new ExceptionalActor
  eActr start

  eActr ! "any message1"
  eActr ! "bad"
  eActr ! "any message2"
  eActr ! "impossible"
  eActr ! "any message3"
}