package core.http

import org.apache.commons.fileupload.servlet.ServletFileUpload
import core.Methods
import scala.collection.mutable.ListBuffer
import org.apache.commons.fileupload.FileItemStream
import org.apache.commons.fileupload.util.Streams
import java.io.File
import java.io.FileOutputStream



object Request {
  import javax.servlet.http.HttpServletRequest
  import core.Request
  
  type Method = Methods.Value
  
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