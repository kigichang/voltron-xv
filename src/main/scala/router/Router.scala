package router

import core._
import controllers._

object Router {
 
  def route(path: String)(implicit request: Request): Result = { println(path); path match {
    
    //case Path("GET", "abc", "def", name, pathInfo @_*) => { println(pathInfo.mkString("/")); Application.hello(name) }
    case Path("GET", "abc", "def", name, pathInfo @_*) => Application.hello(name)
    case _  => Application.hello("stranger")
  }}
}