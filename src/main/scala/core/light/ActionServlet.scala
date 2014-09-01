package core.light

import core.http.Request._
import core.http.Response._
import org.apache.commons.lang3.StringUtils._
import scala.util.Try

abstract class ActionServlet extends HttpServlet {

  def route(routee: (Method, List[String]))(implicit request: Request, response: Response): Unit
  
  
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val request: Request = req
    val response: Response = resp
    
    
    val result = Try {
      route(req.method -> split(req.getPathInfo(), '/').toList)(request, response)
    }
    
  }
  
  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) {
    doGet(req, resp)
  }
}