package core.light

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import core._

abstract class ActionServlet extends HttpServlet {

  def route()
  
  
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val request: Request = req
    //val response: Response = resp
    
  }
  
  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) {
    doGet(req, resp)
  }
}