package act

/**
 * http://stackoverflow.com/questions/1007371/processing-concurrently-in-scala
 */

import scala.actors.Actor
import scala.actors.Actor._

case class Request(sender: Actor, payload: String)
case class Ready(sender: Actor)
case class Result(result: String)
case object Stop


class ProducerConsumer extends App {

  def consumer(n: Int) = actor {
    loop {
      react {
        case Ready(sender) =>
          sender ! Ready(self)
        case Request(sender, payload) =>
          println("request to consumer " + n + " with " + payload)
          // some silly computation so the process takes awhile
          val result = ((payload + payload + payload) map {
            case '0' => 'X';
            case '1' => "-";
            case c => c
          }).mkString
          sender ! Result(result)
          println("consumer " + n + " is done processing " + result)
        case Stop => exit
      }
    }
  }

  // a pool of 10 consumers
  val consumers = for (n <- 0 to 10) yield consumer(n)

  val coordinator = actor {
    loop {
      react {
        case msg@Request(sender, payload) =>
          consumers foreach {
            _ ! Ready(self)
          }
          react {
            // send the request to the first available consumer
            case Ready(consumer) => consumer ! msg
          }
        case Stop =>
          consumers foreach {
            _ ! Stop
          }
          exit
      }
    }
  }


  // a little test loop - note that it's not doing anything with the results or telling the coordinator to stop
  for (i <- 0 to 1000) coordinator ! Request(self, i.toString)

}