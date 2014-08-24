object test2 {
  
  def apply(n: Int) = n + 10
  
  def unapply(n: Int) = Option(n, n + 20)
}

object test extends App {

  val a = "/a/b/c"
    
  val b = Array("a", "b", "c")
  
  /*b match {
    case org.apache.commons.lang3.StringUtils(a, '/') => println("ok")
    case _ => println("not ok")
    
  }*/
  

  val c = test2(30)
  
  c match {
    case test2(n) => println("ok - " + n)
    case _ => println("abc")
  }
  
}