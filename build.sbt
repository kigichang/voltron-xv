 import com.earldouglas.xsbtwebplugin.WebPlugin.webSettings

name := """voltron-xv"""

version := "1.0"

scalaVersion := "2.11.1"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" % "scalatest" % "2.1.6" % "test"

// Servlet
libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided"

libraryDependencies ++= Seq("org.apache.tomcat.embed" % "tomcat-embed-core" % "7.0.22" % "container",
    "org.apache.tomcat.embed" % "tomcat-embed-logging-juli" % "7.0.22" % "container",
    "org.apache.tomcat.embed" % "tomcat-embed-jasper" % "7.0.22" % "container"
)

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.3.2"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.3"

javacOptions ++= Seq("-source", "1.7")

webSettings