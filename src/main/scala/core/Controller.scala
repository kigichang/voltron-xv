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
  def test(block: () => String) = {
    (req: Request, resp: Response) => block
  }
}