package core

trait Controller {

  val todo = new NotFound
  
}

object ActionBuilder {
  
}

object Action {
  
  def apply(block: => Result) = {
    block
  }
  
  def apply(block: Request => String)(implicit req: Request) = { 
    block(req)
  }
}