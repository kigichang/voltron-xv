package core.data

case class Column[T](name: String, options: ColumnOption[T]*) {
  
}

object Column extends App {

  
  
  def column[T](name: String, options: ColumnOption[T])(implicit col: ColumnType[T]): Column[T] = {
    Column[T](name, options)
  }
  
  import core.data.ColumnType._
  val a = column[List]("test", ColumnOption.NotNull)
}