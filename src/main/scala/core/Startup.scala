package core

import javax.servlet.Filter
import javax.servlet.FilterConfig
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
//import core._
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import controllers._
import router._
import org.apache.commons.lang3.StringUtils._
//import org.apache.commons.fileupload.servlet.ServletFileUpload
import scala.util.Try
import core.http.Request._
import core.http.Response._

class Startup extends Filter { self => 

  override def init(config: FilterConfig) {
    
  }
  
  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
    implicit val req: Request = request.asInstanceOf[HttpServletRequest]
    implicit val resp: Response = response.asInstanceOf[HttpServletResponse]
    
    println(req.requestTime)
    println(req.queryString)
    println(req.requestURI)
    println(req.getContextPath())

    for { c <- req.cookie("abc") } { println(c)}
    
    val path = replace(req.requestURI , req.contextPath , "")
    
    val result = Try {
      Router.route(req.method -> split(path, '/').toList)
    }
    
    
  }
  
  override def destroy() {
    
  }
}