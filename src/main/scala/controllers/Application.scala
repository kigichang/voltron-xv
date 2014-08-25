package controllers

import core._
import views._
import core.http.Cookie._

object Application extends Controller {

  def hello(name: String) = Action {
    Ok(Main(name)).withCookie(Cookie("abc", Some(System.currentTimeMillis())))
  }
  
  
  
}