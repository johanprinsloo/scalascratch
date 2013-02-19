package act

import actors.{TIMEOUT, Actor}
import actors.Actor._

private case class Ping( client: Actor, update: Int )
private case class Pulse()
case class Subscribe( actor: Actor )
case class Unsubscribe( actor: Actor )

class PulseActor extends Actor {

  def act = eventloop {
        case ping: Ping => { sleep(ping.update); ping.client ! Pulse }
        case "exit" => exit()
        case m => println("Unknown message " + m +
          " to PulseActor - this incident will be reported..")
  }
  def sleep(millis: Long) =
    receiveWithin(millis) {
      case TIMEOUT =>
        println("PulseActor pulse @"  + System.currentTimeMillis )
  }
}

class ServiceActor extends Actor {

  var observers: Set[Actor] = Set.empty
  val pulseactor = new PulseActor
  val update = 2000

  def act = {
    pulseactor.start
    pulseactor ! new Ping( this, update )
    loop {
      react {
        case sub: Subscribe => observers += sub.actor
        case unsub: Unsubscribe => observers -= unsub.actor
        case Pulse => pulse
        case "exit" => println("ProfileActor exit @"  + System.currentTimeMillis ); exit()
        case m => println("Unknown message " + m + " to ProfileActor - this incident will be reported..")
      }
    }
  }


  def pulse {
    println("ProfileActor pulse @"  + System.currentTimeMillis )
    //cpuload = CPUprofile.getCPUload.getOrElse{ List(0.0) }  //real work
    observers foreach { observer => observer ! "CPUloadReport( cpuload )" }
    pulseactor ! Ping(this, update)
  }
}

object Exercise extends App {
  val deamon = new ServiceActor
  deamon.start
}