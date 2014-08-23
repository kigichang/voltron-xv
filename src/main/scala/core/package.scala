package object core {
  implicit def firstElementToOption[T](array: Array[T]) = if (array != null && array.length > 0) Some(array(0)) else None
  
}