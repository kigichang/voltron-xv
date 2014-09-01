package core

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import core.http.Cookie._
import org.apache.commons.fileupload.FileItemStream

abstract class Request(request: HttpServletRequest) extends HttpServletRequestWrapper(request) {
  
  val requestTime = System.currentTimeMillis()
  
  val queryString = request.getQueryString()
  
  val requestURI = request.getRequestURI()
  
  val contextPath = request.getContextPath()
  
  val method = Methods.fromRaw(request.getMethod())
  
  // Cookies
  protected lazy val cookies = new Cookies(request.getCookies())
  def cookie(name: String) = cookies(name)
  
  protected lazy val httpSession = request.getSession()
  
  def session(name: String): Option[AnyRef] = Option(httpSession.getAttribute(name))
  
  def session(name: String, value: Option[Any]) = value match {
    case Some(x) => httpSession.setAttribute(name, x)
    case None => httpSession.setAttribute(name, None)
  }
  
  
  def attr(name: String): Option[AnyRef] = Option(request.getAttribute(name))
  
  def attr(name: String, value: Option[Any]) = value match {
    case Some(x) => request.setAttribute(name, x)
    case None => request.setAttribute(name, null)
  }
  
  
  def apply(name: String) = param(name)
   
  def update(name: String, value: Option[Any]) = attr(name, value)
  
  
  // virtual functions
  def param(name: String): Option[String]
  def params(name: String): Option[List[String]]
  
  def file(name: String): Option[FileItemStream]
  def files(name: String): Option[List[FileItemStream]] 
}

