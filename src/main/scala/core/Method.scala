package core

object Methods extends Enumeration {

  val GET, POST, UNIMPLEMENT = Value
  
  def fromRaw(value: String) = value.toUpperCase() match {
  
    case "GET" => GET
    case "POST" => POST
    case _ => UNIMPLEMENT
  }
  
  def toRaw(value: Value): Option[String] = value match {
    case GET => Some("GET")
    case POST => Some("POST")
    case _ => None
  }
}