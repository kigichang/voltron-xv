object test2 {
  
  def apply(n: Int) = n + 10
  
  def unapply(n: Int) = Option(n, n + 20)
}

class Path {
  
  def apply(elm: String*) = elm.mkString("/")
  def unapplySeq(path: String): Option[Seq[String]] = Some(org.apache.commons.lang3.StringUtils.split(path, '/')) 
}

case class GET(path: String) extends Path
case class POST(path: String) extends Path

object test extends App {

  val a = GET("/a/b/c/d/f")
  
  
  

  val c = test2(30)
  
  c match {
    case test2(n) => println("ok - " + n)
    case _ => println("abc")
  }
  
}