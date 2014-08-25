package core

import javax.servlet.Filter
import javax.servlet.FilterConfig
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import core._
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import controllers._
import router._

class Startup extends Filter { self => 

  override def init(config: FilterConfig) {
    
  }
  
  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
    implicit val req: Request = request.asInstanceOf[HttpServletRequest]
    
    println(req.requestTime)
    println(req.pathInfo)
    println(req.queryString)
    println(req.requestURI)
    println(req.getContextPath())
    println(title)
    
    for { c <- req.cookie("abc") } { println(c)}
    
    val path = org.apache.commons.lang3.StringUtils.replace(req.requestURI , req.contextPath , "")
    
    implicit val result = Router.route(req.method + path)
    val resp: Response = response.asInstanceOf[HttpServletResponse]
    
    result match {
      case Ok(contents) => resp.getWriter().println(contents)
    }
    
  }
  
  override def destroy() {
    
  }
}