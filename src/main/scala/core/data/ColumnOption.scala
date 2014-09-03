package core.data

abstract class ColumnOption[+T]

object ColumnOption {
  case object NotNull extends ColumnOption[Nothing]
  case object Nullable extends ColumnOption[Nothing]
  
  case object Primary extends ColumnOption[Nothing]
  
  case object AutoIncrement extends ColumnOption[Nothing]
  
  case class Default[T](val defaultValue: T) extends ColumnOption[T]
  
}