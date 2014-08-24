package core

///case class Cookie(name: String, var value:Option[String]) 

class A {
  
  private var aa = 10
  
  def a = aa
  
  def a_ = (v: Int) => { aa = v}
}

class B extends App {
  
  val a = new A()
  
  println(a)
  
  a.a = 40
  
}