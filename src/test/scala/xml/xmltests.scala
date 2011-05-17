package xml

import scala.xml._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class xmltests extends FlatSpec with ShouldMatchers {

  "a XML string" should "be instantiable" in {
    val someXML = XML.loadString(xmlscratch.structXMLInAString)
    assert( someXML.isInstanceOf[scala.xml.Elem] )
  }

  "a XML literal" should "be instantiable" in {
    assert( xmlscratch.structXMLInxml.isInstanceOf[scala.xml.Elem] )
  }

}