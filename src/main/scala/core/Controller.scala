package core

trait Controller {

  val todo = {
    
  }
  
  def before = todo
  
  def after = todo
  
  def rendering = todo
  
  def rendered = todo
  
}

object Action {
  
  def apply(block: => Result) = {
    block
  }
  
  def apply(block: Request => String)(implicit req: Request) = { 
    block(req)
  }
}