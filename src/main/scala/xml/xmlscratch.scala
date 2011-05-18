package xml

/**
 * http://programming-scala.labs.oreilly.com/ch10.html
 */

import java.text.SimpleDateFormat
import java.util.Date
import scala.xml._

object xmlscratch {

  val structXMLInAString = """
          <modelstructure>
          <graphics>
          <icon style="3D">icn504.svg</icon>
          <coords>
                 <x>654</x>
                 <y>6546</y>
          </coords>
          </graphics>
          </modelstructure>"""

  val structXMLInxml =
          <modelstructure>
          <graphics>
          <icon style="3D">icn504.svg</icon>
          <coords>
                 <x>654</x>
                 <y>6546</y>
          </coords>
          </graphics>
          </modelstructure>
}

object Config {
  val atomPath = "atom-example.xml"
}

class Post(val title: String, val body: String, val updated: Date) {
  lazy val dashedDate = {
    val dashed = new SimpleDateFormat("yy-MM-dd")
    dashed.format(updated)
  }

  lazy val atomDate = {
    val rfc3339 = new SimpleDateFormat("yyyy-MM-dd'T'h:m:ss'-05:00'")
    rfc3339.format(updated)
  }

  lazy val slug = title.toLowerCase.replaceAll("\\W", "-")
  lazy val atomId  = "tag:example.com," + dashedDate + ":/" + slug
}

class AtomFeed(posts: Seq[Post]) {
  val feed =
    <feed xmlns="http://www.w3.org/2005/Atom">
      <title>My Blog</title>
      <subtitle>A fancy subtitle.</subtitle>
        <link href="http://example.com/"/>
        <link href="http://example.com/atom.xml" rel="self"/>
      <updated>{posts(0).atomDate}</updated>
      <author>
        <name>John Doe</name>
        <uri>http://example.com/about.html</uri>
      </author>
      <id>http://example.com/</id>
      {for (post <- posts) yield
      <entry>
        <title>{post.title}</title>
          <link href={"http://example.com/" + post.slug + ".html"} rel="alternate"/>
        <id>{post.atomId}</id>
        <updated>{post.atomDate}</updated>
        <content type="html">{post.body}</content>
        <author>
          <name>John Doe</name>
          <uri>http://example.com/about.html</uri>
        </author>
      </entry>}
    </feed>

  def write = XML.save(Config.atomPath, feed, "UTF-8", true, null)

  def outxml : String = XML.toString
}
