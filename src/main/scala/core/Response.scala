package core

import javax.servlet.http.HttpServletResponseWrapper
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.Cookie

case class Response(response: HttpServletResponse) extends HttpServletResponseWrapper(response) {

  def cookie(name: String, value: String = "", maxAge: Int = -1, path: String = "/", domain: String = null, secure: Boolean = false) {
    val cookie = new Cookie(name, value)
    cookie.setDomain(domain)
    cookie.setMaxAge(maxAge)
    cookie.setPath(path)
    cookie.setSecure(secure)
    response.addCookie(cookie)
  }
  
  
  def removeCookie(name: String) {
    cookie(name, null, 0)
  }
  
  def clearCookie(implicit req: Request) = req.request.getCookies().foreach(cookie => {
	  	cookie.setMaxAge(0) 
	  	response.addCookie(cookie)
	  })
}