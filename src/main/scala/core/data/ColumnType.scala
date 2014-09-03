package core.data

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.{Date => DateTime}
import java.sql.Date
import java.sql.Time
import java.sql.Timestamp

abstract class ColumnType[T] {
  val sqlType: Int
  def param(statement: PreparedStatement, index: Int, value: T)
  def update(result: ResultSet, index: Int, value: T)
  def result(result: ResultSet, index: Int): T
  
  def setNull(statement: PreparedStatement, index: Int) = statement.setNull(index, sqlType)
}

class StringColumnType extends ColumnType[String] {
  override val sqlType = java.sql.Types.VARCHAR
  
  override def param(statement: PreparedStatement, index: Int, value: String) = statement.setString(index, value)
  override def update(result: ResultSet, index: Int, value: String) = result.updateString(index, value)
  override def result(result: ResultSet, index: Int): String = result.getString(index)
}

class BigDecimalColumnType extends ColumnType[BigDecimal] {
  import scala.math.BigDecimal._
  
  override val sqlType = java.sql.Types.DECIMAL
  override def param(statement: PreparedStatement, index: Int, value: BigDecimal) = statement.setBigDecimal(index, value.bigDecimal)
  override def update(result: ResultSet, index: Int, value: BigDecimal) = result.updateBigDecimal(index, value.bigDecimal)
  override def result(result: ResultSet, index: Int): BigDecimal = result.getBigDecimal(index)
}

class LongColumnType extends ColumnType[Long] {
  override val sqlType = java.sql.Types.BIGINT
  override def param(statement: PreparedStatement, index: Int, value: Long) = statement.setLong(index, value)
  override def update(result: ResultSet, index: Int, value: Long) = result.updateLong(index, value)
  override def result(result: ResultSet, index: Int): Long = result.getLong(index)
}

class IntColumnType extends ColumnType[Int] {
  override val sqlType = java.sql.Types.INTEGER
  override def param(statement: PreparedStatement, index: Int, value: Int) = statement.setInt(index, value)
  override def update(result: ResultSet, index: Int, value: Int) = result.updateInt(index, value)
  override def result(result: ResultSet, index: Int): Int = result.getInt(index)
}

class ShortColumnType extends ColumnType[Short] {
  override val sqlType = java.sql.Types.SMALLINT
  override def param(statement: PreparedStatement, index: Int, value: Short) = statement.setShort(index, value)
  override def update(result: ResultSet, index: Int, value: Short) = result.updateShort(index, value)
  override def result(result: ResultSet, index: Int): Short = result.getShort(index)
}

class FloatColumnType extends ColumnType[Float] {
  override val sqlType = java.sql.Types.FLOAT
  override def param(statement: PreparedStatement, index: Int, value: Float) = statement.setFloat(index, value)
  override def update(result: ResultSet, index: Int, value: Float) = result.updateFloat(index, value)
  override def result(result: ResultSet, index: Int): Float = result.getFloat(index)
}

class DoubleColumnType extends ColumnType[Double] {
  override val sqlType = java.sql.Types.DOUBLE
  override def param(statement: PreparedStatement, index: Int, value: Double) = statement.setDouble(index, value)
  override def update(result: ResultSet, index: Int, value: Double) = result.updateDouble(index, value)
  override def result(result: ResultSet, index: Int): Double = result.getDouble(index)
}

class DateTimeColumnType extends ColumnType[DateTime] {
  implicit def dateTimeToTimestamp(dt: DateTime) = if (dt != null) new java.sql.Timestamp(dt.getTime()) else null
  
  override val sqlType = java.sql.Types.TIMESTAMP
  override def param(statement: PreparedStatement, index: Int, value: DateTime) = statement.setTimestamp(index, value)
  
  override def update(result: ResultSet, index: Int, value: DateTime) = {
    if (value != null) result.updateTimestamp(index, value) else result.updateNull(index)
  }
  
  override def result(result: ResultSet, index: Int): DateTime = {
    val ts = result.getTimestamp(index)
    if (ts != null) new DateTime(ts.getTime()) else null
  }
}

class DateColumnType extends ColumnType[Date] {
  override val sqlType = java.sql.Types.DATE
  override def param(statement: PreparedStatement, index: Int, value: Date) = statement.setDate(index, value)
  
  override def update(result: ResultSet, index: Int, value: Date) = result.updateDate(index, value)
  
  override def result(result: ResultSet, index: Int) = result.getDate(index)
}

class TimeColumnType extends ColumnType[Time] {
  override val sqlType = java.sql.Types.TIME
  
  override def param(statement: PreparedStatement, index: Int, value: Time) = statement.setTime(index, value)
  override def update(result: ResultSet, index: Int, value: Time) = result.updateTime(index, value)
  override def result(result: ResultSet, index: Int) = result.getTime(index)
}

class TimestampColumnType extends ColumnType[Timestamp] {
  override val sqlType = java.sql.Types.TIMESTAMP
  
  override def param(statement: PreparedStatement, index: Int, value: Timestamp) = statement.setTimestamp(index, value)
  override def update(result: ResultSet, index: Int, value: Timestamp) = result.updateTimestamp(index, value)
  override def result(result: ResultSet, index: Int): Timestamp = result.getTimestamp(index)
}

class BooleanColumnType extends ColumnType[Boolean] {
  override val sqlType = java.sql.Types.BOOLEAN
  
  override def param(statement: PreparedStatement, index: Int, value: Boolean) = statement.setBoolean(index, value)
  override def update(result: ResultSet, index: Int, value: Boolean) = result.updateBoolean(index, value)
  override def result(result: ResultSet, index: Int): Boolean = result.getBoolean(index)
}

object ColumnType {
  implicit val stringType = new StringColumnType()
  implicit val bigdecimalType = new BigDecimalColumnType()
  implicit val longType = new LongColumnType()
  implicit val intType = new IntColumnType()
  implicit val shortType = new ShortColumnType()
  implicit val floatType = new FloatColumnType()
  implicit val doubleType = new DoubleColumnType()
  implicit val datetimeType = new DateTimeColumnType()
  implicit val dateType = new DateColumnType()
  implicit val timeType = new TimeColumnType()
  implicit val timestampType = new TimestampColumnType()
  implicit val booleanType = new BoolColumnType()
}