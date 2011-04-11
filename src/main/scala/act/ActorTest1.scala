package org.scratch.act

import actors.Actor

case class Register(actor: Actor)
case class Unregister(actor: Actor)
case class Message( contents: String )

object ActorRegistry extends Actor{
  var registry: Set[Actor] = Set.empty

  def act() {
    loop{
      react{
        case reg: Register => register( reg.actor )
        case unreg: Unregister => unregister( unreg.actor )
        case message: Message => fire( message )
      }
    }
  }

  def register(reg: Actor) {
    //println("register" + reg)
    registry += reg }

  def unregister(unreg: Actor) { registry -= unreg }

  def fire(msg: Message){
    val starttime = System.currentTimeMillis()
    println( "start notification loop: " + starttime )
    registry.par.foreach { client => client ! msg }
    val endtime = System.currentTimeMillis()
    println( "end notification loop: " + endtime )
    println("elapsed: " + (endtime - starttime) + " ms")
  }
}

class Client(id: Long) extends Actor{
  var lastmsg = ""
  def act() {
    loop{
      react{
        case msg: Message => got(msg.contents)
      }
    }
  }
  def got(msg: String) {
    lastmsg = msg
    //println(msg)
  }
}

object Main extends App {

  ActorRegistry.start

  val init1 = System.currentTimeMillis()

  (1 to 1000000).par.foreach{ i =>
    var client = new Client(i)
    client.start
    ActorRegistry ! Register( client )
  }

  val init2 = System.currentTimeMillis()
  println("Init time: " +(init2 - init1) + " ms")


  ActorRegistry ! Message("One")

  Thread.sleep(6000)

  ActorRegistry ! Message("Two")

  Thread.sleep(6000)

  ActorRegistry ! Message("Three")


}