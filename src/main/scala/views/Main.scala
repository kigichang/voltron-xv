package views

import core._

case class Main(name: String) extends View {

  val contents = 
    
   """
    <html>
	  <head><title>test</title>
      </head>
	  <body>
	  	hello, <b>""" + name + """</b>
	  </body>
    </html>
    
    """
}