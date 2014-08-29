package controllers

import core._
import views._
import core.http.Cookie._

object Application extends Controller {

  
  def hello(name: String)(implicit request: Request): Result = {
    Ok(s"hello, $name")
  }
  
}