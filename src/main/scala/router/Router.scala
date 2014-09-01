package router

import controllers._
import core.Methods._
import core.http.Request._
import core.http.Response._

object Router {
 
  def route(routee: (Method, List[String]))(implicit request: Request, response: Response): Unit = routee match {
    
    case (GET, "abc" :: name :: xx) => Application.hello(name)
    
    case _ => ok("others")
  } 
}