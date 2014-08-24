package core

trait View {

  def contents: String
  
  def display[T](contents: Traversable[T]) = contents.mkString
  
  def display[T](contents: Iterable[T]) = contents.mkString
  
  def display(block: => String) = block
  
}