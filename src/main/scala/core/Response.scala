package core

import javax.servlet.http.HttpServletResponseWrapper
import javax.servlet.http.HttpServletResponse
import core.http.Cookie._

class Response(response: HttpServletResponse) extends HttpServletResponseWrapper(response) {

  def withCookie(cookie: Cookie, others: Cookie*): Response = {
    (Seq(cookie) ++ others).foreach(c => this.addCookie(c))
    this
  }
  
  def encoding_= (encoding: String): Response = { response.setCharacterEncoding(encoding); this }
  
  def encodeing: String = response.getCharacterEncoding()
  
  def status: Int = response.getStatus()
  def status_= (status: Int): Response = { response.setStatus(status); this }
  
  
  def asHtml: Response = { response.setContentType("text/html"); this }
  
  
}
