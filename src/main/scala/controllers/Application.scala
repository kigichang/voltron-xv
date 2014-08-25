package controllers

import core._
import views._

object Application extends Controller {

  def hello(name: String) = Action {
    Ok(Main(name)).withCookie(Cookie("abc", System.currentTimeMillis().toString))
  }
  
}