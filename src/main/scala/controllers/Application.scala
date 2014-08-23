package controllers

import core._

object Application extends Controller {

  def hello(name: String)(implicit request: Request, response: Response) {
    
    response.getWriter().println(s"hello, $name")
  }
  
  
}