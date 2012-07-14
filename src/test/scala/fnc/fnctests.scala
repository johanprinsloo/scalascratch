package org.scratch.fnc

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers


class fnctests extends FlatSpec with ShouldMatchers {

  "a Filematcher" should "list some files" in {
    val matches = FileMatcher.filesEnding("")
    println( matches.toList )
    matches.size should be > (0)

    for( fileitem <- matches ){
     println(fileitem)
    }
  }

    "a Filematcher" should "list correct files" in {
    val matches = FileMatcher.filesEnding(".sbt")
    println( matches.toList )
    matches.size should be  (1)

    for( fileitem <- matches ){
     println(fileitem)
    }
  }
}
