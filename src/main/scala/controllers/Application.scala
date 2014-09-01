package controllers

import core.http.Response._
import core.http.Request._
import core.http.Cookie._
import views._


object Application extends Controller {

  
  def hello(name: String)(implicit request: Request, response: Response): Unit = {
    ok(s"hello, $name")
  }
  
}