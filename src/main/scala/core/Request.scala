package core

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import core.http.Cookie._
import org.apache.commons.fileupload.FileItem
import org.apache.commons.fileupload.util.Streams
import org.apache.commons.fileupload.servlet.ServletFileUpload
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import org.apache.commons.fileupload.FileItemStream
import java.io.File
import java.io.FileOutputStream
import javax.servlet.ServletRequest

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

object Request {
  
  class GeneralRequest(request: HttpServletRequest) extends Request(request) {
  
    override def param(name: String): Option[String] = Option(request.getParameter(name))
    override def params(name: String): Option[List[String]] = Option(
      if (request.getParameterValues(name) != null) request.getParameterValues(name).toList
      else null)
    
    override def file(name: String) = None
    override def files(name: String) = None
  }
  
  
  
  class MultiPartRequest(request: HttpServletRequest) extends Request(request) {
  
    require(ServletFileUpload.isMultipartContent(request))
  
    override val method = Methods.POST 
  
    private[this] val paramMap = scala.collection.mutable.Map.empty[String, ListBuffer[String]]
    private[this] val fileMap = scala.collection.mutable.Map.empty[String, ListBuffer[FileItemStream]]
  
    private[this] val upload = new ServletFileUpload();
  
    private def processMap[T](name: String, value: T, map: scala.collection.mutable.Map[String, ListBuffer[T]]) = map.get(name) match {
      case None => map += (name -> ListBuffer(value))
      case Some(lst) => lst += value
    }
  
    private[this] val iterator = upload.getItemIterator(request)
  
    while(iterator.hasNext()) {
      val item = iterator.next()
    
      if (item.isFormField()) {
        if (item.isFormField()) {
          processMap(item.getFieldName(), Streams.asString(item.openStream()), paramMap)
        }
        else {
          processMap(item.getFieldName(), item, fileMap)
        }
      }
    }
  
    override def param(name: String): Option[String] = for (lst <- paramMap.get(name)) yield lst(0)
    override def file(name: String): Option[FileItemStream] = for (lst <- fileMap.get(name)) yield lst(0)
  
    override def params(name: String): Option[List[String]] = for(lst <- paramMap.get(name)) yield lst.toList
    override def files(name: String): Option[List[FileItemStream]] = for (lst <- fileMap.get(name)) yield lst.toList
  
    def writeFile(file: File, item: FileItemStream) = Streams.copy(item.openStream(), new FileOutputStream(file), true)
    def writeFile(fileName: String, item: FileItemStream) = Streams.copy(item.openStream(), new FileOutputStream(fileName), true)
  }
  
  implicit def httpRequestToRequest(request: HttpServletRequest) = apply(request)
  
  def apply(request: HttpServletRequest): Request = 
    if (ServletFileUpload.isMultipartContent(request)) new MultiPartRequest(request)
    else new GeneralRequest(request)
  
  
}



/*
class Request {
	
  
  implicit class RRequest(request: HttpRequest) extends HttpServletRequestWrapper(request) {
    
    val requestTime = System.currentTimeMillis()
  
    val queryString = Option(request.getQueryString())
  
    val pathInfo = Option(request.getPathInfo())
  
    val requestURI = request.getRequestURI()
  
    val contextPath = request.getContextPath()
  
    val method = Methods.fromRaw(request.getMethod())
    
    protected lazy val cookies = new Cookies(request.getCookies())
  
    def param(parameter: String): Option[String] = Option(request.getParameter(parameter))

    def params(parameter: String) : List[String] = 
      if (request.getParameterValues(parameter) != null) request.getParameterValues(parameter).toList
      else List()
  
    def cookie(name: String) = cookies(name)
  
    protected lazy val httpSession = request.getSession()
  
    def session(name: String): Option[AnyRef] = Option(httpSession.getAttribute(name))
  
    def session(name: String, value: Option[Any]) = for(v <- value) {
      httpSession.setAttribute(name, if (v != null) Some(v) else null) 
    }
  
    def attr(name: String): Option[AnyRef] = Option(request.getAttribute(name))
  
    def attr(name: String, value: Option[Any]) = for (v <- value) {
      request.setAttribute(name, if (v != null) Some(v) else null)
    }
  
    def apply(name: String) = param(name)
   
    def update(name: String, value: Option[Any]) = attr(name, value)
  }
}
*/