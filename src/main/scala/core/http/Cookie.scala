package core.http

object Cookie {
  
  type Cookie = RichCookie
  type HttpCookie = javax.servlet.http.Cookie
  
  implicit class RichCookie(private[core] val cookie: HttpCookie) {
  
    def name = cookie.getName()
    
    def value = Option(cookie.getValue())
    def value_= (newValue: Option[Any]) = for (v <- newValue) { 
      if (v != null) cookie.setValue(v.toString) 
      else cookie.setValue(null) 
    }
    
    def maxAge = cookie.getMaxAge()
    def maxAge_= (expiry: Int) = cookie.setMaxAge(expiry)
  
    def path = Option(cookie.getPath())
    def path_= (uri: Option[String]) = cookie.setPath(uri.orNull)
  
    def domain = Option(cookie.getDomain())
  	def domain_= (domain: Option[String]) = for (d <- domain) cookie.setDomain(d)
  
  	def secure = cookie.getSecure()
  	def secure_= (flag: Boolean) = cookie.setSecure(flag)
  
  	def httpOnly = cookie.isHttpOnly()
  	def httpOnly_= (isHttpOnly: Boolean) = cookie.setHttpOnly(isHttpOnly)
  
  	def comment = cookie.getComment()
  	def comment_= (purpose: Option[String]) = cookie.setComment(purpose.orNull)
  
  	def version = cookie.getValue()
  	def version_= (v: Int) = cookie.setVersion(v)
  	
  }
  
  def Cookie(name: String, value: Option[Any], 
		  	maxAge: Int = -1, path: Option[String] = None, 
		  	domain: Option[String] = None, secure: Boolean = false, 
		  	isHttpOnly: Boolean = false, purpose: Option[String] = None, version: Int = 0): RichCookie = {
    
    val cookie:Cookie = new HttpCookie(name, null)
    
    cookie.value = value
    cookie.maxAge = maxAge
    cookie.path = path
    cookie.domain = domain
    cookie.secure = secure
    cookie.httpOnly = isHttpOnly
    cookie.comment = purpose
    cookie.version = version
    cookie
  }
  
  final class Cookies(httpcookies: Array[HttpCookie]) {
  
    private[core] val cookies = scala.collection.mutable.Map.empty[String, Cookie]
  
    if (httpcookies != null) {
      httpcookies foreach { cookie => 
        cookies += (cookie.getName() -> cookie)
      }
    }
  
    def this() = this(null)
  
    def find(name: String) = cookies.get(name)
  
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
  
  implicit def cookieToHttpCookie(cookie: Cookie): HttpCookie = cookie.cookie 
}


