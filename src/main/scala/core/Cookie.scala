package core
import javax.servlet.http.{Cookie => HttpCookie}


final class CookieWrapper(private[core] val cookie: HttpCookie) {
    
  def this(name: String, value: Option[String], 
		  	maxAge: Int = -1, path: Option[String] = None, 
		  	domain: Option[String] = None, secure: Boolean = false, 
		  	isHttpOnly: Boolean = false, purpose: Option[String] = None, version: Int = 0) {
    
	this(new HttpCookie(name, value.orNull))
    
	
	cookie.setComment(purpose.orNull)
	cookie.setDomain(domain.orNull)
	cookie.setHttpOnly(isHttpOnly)
	cookie.setMaxAge(maxAge)
	cookie.setPath(path.orNull)
	cookie.setSecure(secure)
	cookie.setVersion(version)    
  }
  
  
  def name = cookie.getName()
  
  def value = Option(cookie.getValue)
  def value_= (newValue: Option[String]) = cookie.setValue(newValue.orNull)
  
  def maxAge = cookie.getMaxAge()
  def maxAge_= (expiry: Int) = cookie.setMaxAge(expiry)
  
  def path = Option(cookie.getPath())
  def path_= (uri: Option[String]) = cookie.setPath(uri.orNull)
  
  def domain = Option(cookie.getDomain())
  def domain_= (domain: Option[String]) = cookie.setDomain(domain.orNull)
  
  def secure = cookie.getSecure()
  def secure_= (flag: Boolean) = cookie.setSecure(flag)
  
  def httpOnly = cookie.isHttpOnly()
  def httpOnly_= (isHttpOnly: Boolean) = cookie.setHttpOnly(isHttpOnly)
  
  def comment = cookie.getComment()
  def comment_= (purpose: Option[String]) = cookie.setComment(purpose.orNull)
  
  def version = cookie.getValue()
  def version_= (v: Int) = cookie.setVersion(v)
}

object Cookie {
  def apply(cookie: HttpCookie): CookieWrapper = new CookieWrapper(cookie)
  def apply(name: String, value: Option[String], 
		  	maxAge: Int = -1, path: Option[String] = None, 
		  	domain: Option[String] = None, secure: Boolean = false, 
		  	isHttpOnly: Boolean = false, purpose: Option[String] = None, version: Int = 0): CookieWrapper = 
		  	  new CookieWrapper(name, value, maxAge, path, domain, secure, isHttpOnly, purpose, version)
}

final class Cookies(httpcookies: Array[HttpCookie]) {
  
  private[core] val cookies = scala.collection.mutable.Map.empty[String, CookieWrapper]
  
  if (httpcookies != null) {
    httpcookies foreach { cookie => 
       cookies += (cookie.getName() -> Cookie(cookie))
    }
  }
  
  def this() = this(null)
  
  def find(name: String) = Option(cookies(name))
  
  def apply(name: String) = for(cookie <- find(name); value <- cookie.value) yield value
  
  def += (cookie: Cookie, others: Cookie*) = {
    Seq(cookie) ++ others foreach { c => 
      cookies += (c.name -> c)
    }
    this
  }

  
  def update(cookie: Cookie) = { cookies += (cookie.name -> cookie); this }
  
  def update(name: String, value: Option[String]): Cookies = update(Cookie(name, value))
  
  def remove(name: String): Cookies = update(Cookie(name, None, 0))
  
  def remove(cookie: Cookie): Cookies = {cookie.value = None; cookie.maxAge = 0; update(cookie) }
  
}