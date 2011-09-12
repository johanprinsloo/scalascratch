package par

import collection.parallel.ParSeq

/**
 * from the scaladays talk on parallel collections
 * and http://www.codng.com/2011/09/parallelism-performance-why-o-notation.html
 */

object DataGen {

  val par = Option(System.getProperty("par")).map(_.toInt)

  def vowel(c: Char) = c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'

  def genNames(len: Int, names: Seq[String]): Seq[String] = if (len == 0) names else
    (for (s <- genNames(len - 1, names); c <- 'a' to 'z') yield {
      if (s.length == 0) s + c
      else if (vowel(s.last) && !vowel(c)) s + c
      else if (!vowel(s.last) && vowel(c)) s + c
      else s
    }).distinct
}

class ParString {

}

class Pester {
  val names = DataGen.genNames(3, Array("")).filter(_.length > 1)
  val surnames = DataGen.genNames(4, Array("")).filter(_.length > 2)

  def search_sinO2 : Seq[(String, String)] = {
    for {
      s <- surnames
      n <- names
      if s endsWith n
    } yield (n , s)
  }


  def search_parO2 : ParSeq[(String, String)] = {

    for {
      s <- surnames.par
      n <- names.par
      if s endsWith n
    } yield (n , s)
  }


  def search_sinO1 : Seq[(String, String)] = {

    val suffixMap = collection.mutable.Map[String, List[String]]().withDefaultValue(Nil)
    for (s <- surnames; i <- 0 until s.length; suffix = s.substring(i))
              suffixMap(suffix) = s :: suffixMap(suffix)

    for {
      n <- names
      s <- suffixMap(n)
      if s endsWith n
    } yield (n , s)
  }


}