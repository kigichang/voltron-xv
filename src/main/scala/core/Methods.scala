package core


object GET {
  def apply(path: String)(implicit request: Request, response: Response) {
    if (request.getMethod() == "GET") {
      
    }
  }
  
}

object POST {
  def apply(path: String)(implicit request: Request, response: Response) {
    if (request.getMethod() == "POST") {
      
    }
  }
}