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

object UpperCase {
  def apply(s: String): String = s.toLowerCase()
  def unapply(s: String): Option[String] = if (s.toUpperCase == s) Some(s.toLowerCase()) else None
  //def unapply(s: String): Boolean = s == s.toUpperCase()
}



class Between(val max: Int, val min: Int) {
  
  //def apply(value: Int): Int = value
  def unapplySeq(value: Int): Option[List[Int]] = { 
    println("call unapply", value, max,  min); 
    if (min <= value && value <= max) {
      println("call between ok")
      //Some(List.empty[Int])
      Some(List(min, value, max))
    } 
    else {
      println("call between not ok")
      None
    }
  }
  
  
  
  //def unapply(value: Int) = if(min <= value && value <= max) Some(value) else None
}

object BW {
  def unapply(value: Int): Boolean = (1 <= value && value <= 100)
}

object EMail {

  def apply(u: String, d: String) = { u + "@" + d }
  
  def unapply(s: String): Option[(String, String)] = {
    var parts = s.split("@")
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}

object test extends App {

  val decimal = """(\d+)""".r
  
  
  val a = GET("/a/b/c/d/f")
  val c = test2(30)
  
  c match {
    case test2(n) => println("ok - " + n)
    case _ => println("abc")
  }
  
  "123@kigi.tw" match {
    case EMail(decimal(user), domain) => println("ok : " + user + " and " + domain)
    case _ => println("not ok")
  }

  val k = "GET" -> "123@kigi.tw"
  
  k match {
    case ("GET", EMail(decimal(user), domain)) => println("ok : " + user + " and " + domain)
    case _ => println("not ok")
  }
  
  
  for ((user, domain) <- EMail.unapply("123kigi.tw")) {
    println(user, domain)
  }
  
  val bb = new Between(40, 1)
  
  (300, 30) match {
    case (m @ bb() , n) => println("aa", n, m)
    case _ => println("aa not ok")
  }
  
  
  30 match {
    case bb(n, _*) => println("ok " + n)
    case _ => println("AAA")
  }
  	
  
  
}