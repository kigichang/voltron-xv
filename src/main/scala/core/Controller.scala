package core

trait Controller {

  val todo = Unit
  
}
/*
object ActionBuilder {
  def apply(block1: => Result)(block2: => Result => Result) {
    block2(block1)
  }
}

object Action {
  
  def apply(block: Unit => Result) = {
    block
  }
  
  def apply(block: Request => Result)(implicit req: Request) = { 
    implicit req: Request => block(req)
  }
}
*/