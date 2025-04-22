import sttp.client3.*

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

val LINK_HEADER = "https://en.wikipedia.org"
val PHILOSOPHY_URI: URI = URI("https://en.wikipedia.org/wiki/Philosophy")

val TARGET_LINK = "https://en.wikipedia.org/wiki/Northern_pike"

@main
def main(): Unit =
  println("searching...")
  val path = pathToPhilosophy(URI(TARGET_LINK))
  println("\nfound!\nHere is the path:")
  path.foreach((title, uri) => println(f"-> $title : $uri"))
  println(f"Found in ${path.length} pages.")


/**
 * computes the path to the wikipedia philosophy page given a valid wikipedia uri
 *
 * @param uri the starting uri
 * @return the path as a list of string containing the title of each wikipedia page
 */
def pathToPhilosophy(uri: URI): List[(String, URI)] =
  @tailrec
  def loop(uri: URI, path: List[(String, URI)]): List[(String, URI)] =
    if uri.isPhilosophy then path :+ ("Philosophy" -> PHILOSOPHY_URI)
    else
      val body = uri.getBody
      val title = body.getTitle
      loop(body.findFirstLink.get, path :+ (title -> uri))
  loop(uri, List())





case class URI(uri: String) {
  /**
   * Gets the body of the uri
   *
   * @return the string of the body, not the plain text!
   */
  def getBody: Body =
    val backend = HttpClientSyncBackend()
    println(uri)
    val response = basicRequest
      .body("Wikipedia page")
      .post(uri"$uri").send(backend)
    //println(response.body.toString)
    Body(response.body.toString)

  /**
   * checks  if the uri corresponds to the one of the philosophy wikipedia
   *
   * @return true if it is the philosophy wikipedia
   */
  def isPhilosophy: Boolean =
    uri == "https://en.wikipedia.org/wiki/Philosophy"

}


case class Body(body: String) {
  /**
   * gets the title of the body web page
   * @return the string containing the title
   */
  def getTitle: String =
    //println(body)
    val target = "<span class=\"mw-page-title-main\">"
    val r = body.indexOf(target)
    body.drop(body.indexOf(target) + target.length).takeWhile(_ != '<')

  /**
   * checks if it is the philosophy wikipedia page
   * @return true if the title of the body is Philosophy
   */
  def isPhilosophy: Boolean =
    getTitle == "Philosophy"

  /**
   * gets the first link of the given body web page.
   *
   * @return the link as the full valid wikipedia link or None if no link was found
   */
  def findFirstLink: Option[URI] =
    val link = Try {
      val text = this.getToText
      //println(text)
      val start = text.drop(text.indexOf("<a href=\"/wiki/") + "<a href=\"".length)
      //println(start)
      val r = start.takeWhile(_ != '\"')
      //println(r)
      r
    }
    val ret = link match
      case Failure(exception) => None
      case Success(value) => Some(URI(LINK_HEADER + value))
    ret.ensuring(ret.isEmpty || ret.get.uri.startsWith(LINK_HEADER))


  /**
   * drops the first part of the body containing the tile and other information
   *
   * @return all the body from the beginning of the text part
   */
  def getToText: String =
    val ret = body.drop(body.indexOf("<p>"))
    //println(ret)
    ret

}