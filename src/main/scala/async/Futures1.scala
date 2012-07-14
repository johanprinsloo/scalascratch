package async

import akka.dispatch.{ExecutionContext, Future, Await}
import akka.util.duration._
import java.util.concurrent.Executors

object Futures1 {
  val execService = Executors.newCachedThreadPool()
  implicit val execContext = ExecutionContext.fromExecutorService(execService)

  def main(args: Array[String]) {
    // A Future list of 1's
    val flist: Future[List[Int]] = Future { (1 to 5) map { _: Int => 1 } toList }

    // The goal is to create a new list of Ints in 25 "iterations" across
    // multiple threads
    val result: Future[List[Int]] = (1 to 5).foldLeft(flist) { (acc, _) =>

    // "Loop" 5 times, creating 5 new lists of Ints, but these 'new' lists
    // share most of their content with the previous list
      val fs: IndexedSeq[Future[List[Int]]] = (1 to 5) map { i =>
        acc map { numlist =>
          (i * numlist.sum) :: numlist
        }
      }

      // Reduce the 5 lists we just created back down to one list again, by
      // performing a pairwise sum across them all... in the Future
      Future.reduce(fs) { (a, l) =>
        (a zip l) map { case (i, j) => i + j }
      }
    }

    // Wait for the concurrency to complete and print out the final list
    println(Await.result(result, 1 second))

    // Shut down the execution system that was running our stuff concurrently
    execContext.shutdown()
  }
}
// Prints: List(12000000, 3000000, 750000, 187500, 46875, 3125, 3125, 3125, 3125, 3125)