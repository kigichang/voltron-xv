package core

import javax.servlet.http.HttpServletResponseWrapper
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.Cookie

case class Response(response: HttpServletResponse) extends HttpServletResponseWrapper(response) {

  
}