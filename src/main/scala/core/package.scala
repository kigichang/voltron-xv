package object core {
  
  import javax.servlet.http.HttpServletResponseWrapper
  import core.http.Cookie._
  import javax.servlet.http.HttpServletRequestWrapper
  
  type HttpRequest = javax.servlet.http.HttpServletRequest
  type HttpResponse = javax.servlet.http.HttpServletResponse
  type Method = Methods.Value
  
  implicit def firstElementToOption[T](array: Array[T]) = if (array != null && array.length > 0) Some(array(0)) else None
  implicit def viewToString(view: View) = view.contents
  
  //implicit val title = "title" + System.currentTimeMillis()
  
  implicit class Request(request: HttpRequest) extends HttpServletRequestWrapper(request) {
    
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
  
  
  implicit class Response(response: HttpResponse)(implicit result: Result) extends HttpServletResponseWrapper(response) {
    
  }
  
  

}