package router

import core._
import controllers._
import core.Methods._

object Router {
 
  def route(routee: (Method, List[String]))(implicit request: Request): Result = routee match {
    
    case (GET, "abc" :: name :: xx) => Application.hello(name)
    
    case _ => Ok("others")
  } 
}