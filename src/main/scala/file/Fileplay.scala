package file

import io.Source
import collection.parallel.mutable


object Fileplay extends App {

  val holmesUrl = """http://www.gutenberg.org/cache/epub/1661/pg1661.txt"""
  for (line <- Source.fromURL(holmesUrl).getLines)
  println(line)

  val holmesIterator = Source.fromFile("pg1661.txt").getLines   // holmesIterator: Iterator[String] = non-empty iterator
  holmesIterator.foreach( line => println( line ) )

  val counts = mutable.Map[String, Int]().withDefault(x=>0)
  for (token <- scala.io.Source.fromFile("pg1661.txt").getLines.flatMap(x =>x.split("\\s+"))) counts(token) +=
}