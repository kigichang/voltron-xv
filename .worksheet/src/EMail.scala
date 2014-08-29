object EMail {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(67); 

  def apply(u: String, d: String) = { u + "@" + d };System.out.println("""apply: (u: String, d: String)String""");$skip(153); 
  
  def unapply(s: String): Option[(String, String)] = {
    var parts = s.split("@")
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  };System.out.println("""unapply: (s: String)Option[(String, String)]""")}
}

object test {
  println("Welcome to the Scala worksheet")
  
  

}
