package object core {
  
  import javax.servlet.http.HttpServletResponseWrapper
  
  type HttpRequest = javax.servlet.http.HttpServletRequest
  type HttpResponse = javax.servlet.http.HttpServletResponse
  type Method = Methods.Value
  
  implicit def firstElementToOption[T](array: Array[T]) = if (array != null && array.length > 0) Some(array(0)) else None
  implicit def viewToString(view: View) = view.contents
  
  implicit class Response(response: HttpResponse)(implicit result: Result) extends HttpServletResponseWrapper(response) {
    
  }
  
  

}