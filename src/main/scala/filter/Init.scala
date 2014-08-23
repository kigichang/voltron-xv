package filter

import javax.servlet.Filter
import javax.servlet.FilterConfig
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import core._
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.Cookie

import controllers._

class Init extends Filter { self => 

  override def init(config: FilterConfig) {
    
  }
  
  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
    implicit val req = Request(request.asInstanceOf[HttpServletRequest])
    implicit val resp = Response(response.asInstanceOf[HttpServletResponse])
    
    
    
    println(req.requestTime)
    println(req.pathInfo)
    println(req.queryString)
    println(req.requestURI)
    println(req.getContextPath())

    val path = org.apache.commons.lang3.StringUtils.replace(req.requestURI , req.contextPath , "")
    
    val infos = org.apache.commons.lang3.StringUtils.split(path, '/').toList
    
    
    infos match {
      case List("abc", "def", name, _) => Application.hello(name)
      case _  => Application.hello("stranger")
    }
    
    
  }
  
  override def destroy() {
    
  }
}