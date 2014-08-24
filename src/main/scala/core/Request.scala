package core

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequestWrapper

case class Request(request: HttpServletRequest) extends HttpServletRequestWrapper(request) {
  val requestTime = System.currentTimeMillis()
  
  val queryString = Option(request.getQueryString())
  
  val pathInfo = Option(request.getPathInfo())
  
  val requestURI = request.getRequestURI()
  
  val contextPath = request.getContextPath()
  
  val method = request.getMethod()
  
  def param(parameter: String): Option[String] = Option(request.getParameter(parameter))

  def params(parameter: String) : Option[Array[String]] = Option(request.getParameterValues(parameter))
  
  def cookie(name: String): Option[Cookie] = {
    
    request.getCookies() match {
      case cookies if cookies != null => cookies.filter(_.getName() == name) 
      case _ => None
    }
  }
  
  lazy val internSession = request.getSession()
  
  def session(name: String): Option[AnyRef] = Option(internSession.getAttribute(name))
  
  def session(name: String, value: Any) = internSession.setAttribute(name, value)
  
  def attr(name: String): Option[AnyRef] = Option(request.getAttribute(name))
  
  def attr(name: String, value: Any) = request.setAttribute(name, value)
  
  def apply(name: String) = param(name)
  
  def update(name: String, value: Any) = attr(name, value)
  
}