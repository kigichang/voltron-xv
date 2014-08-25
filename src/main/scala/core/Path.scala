package core

object Path {
  def apply(elms: String*) = elms.mkString("/")  
  def unapplySeq(path: String): Option[Seq[String]] = Some(org.apache.commons.lang3.StringUtils.split(path, "/"))
}
