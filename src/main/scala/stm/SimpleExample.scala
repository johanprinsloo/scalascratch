package stm

import scala.concurrent.stm._
import scala.concurrent.forkjoin.LinkedTransferQueue

object SimpleExample {
  def main(args: Array[String]): Unit = {
    val numThreads = 100
    val startingLatch = new java.util.concurrent.CountDownLatch(1)
    val notifier = new LinkedTransferQueue[Int]()
    val x = Ref(0)
    val threads = for (i <- 1 to numThreads) yield {
      new Thread(new Runnable() {
        def run(): Unit = {
          startingLatch.await()
          for (i <- 1 to 1000) {
            atomic{
              implicit txn =>
                x() = x() + 1
              val y = x()
              Txn.whileCommitting(txnEnd => {
                notifier.put(y)
              })
            }
          }
        }
      })
    }

    val producersDone = new
        java.util.concurrent.atomic.AtomicBoolean(false)
    val reporter = new Thread(new Runnable() {
      def run(): Unit = {
        var expected = 1
        while (!producersDone.get || !notifier.isEmpty) {
          val next = notifier.take()
          if (next != expected) {
            println("Error, got " + next + " expected " + expected)
            return
          }
          expected += 1
        }
      }
    })
    reporter.start()
    threads.foreach(_.start())
    startingLatch.countDown()
    threads.foreach(_.join())
    producersDone.set(true)
    reporter.join()
  }
}