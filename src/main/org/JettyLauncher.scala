package org

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.server.handler.ResourceHandler


object JettyLauncher {
    def main(args: Array[String]) {
        val port = if(System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080

        val baseDir = "src/main/webapp"
        val server = new Server(port)
        val context = new WebAppContext()
        context setContextPath "/"
        context.setResourceBase(baseDir)
        context.setDescriptor(baseDir + "/WEB-INF/web.xml")
        context.addServlet(classOf[Router], "/*")
        context.addServlet(classOf[DefaultServlet], "/")



        val resource_handler = new ResourceHandler()
        resource_handler.setDirectoriesListed(true)
        resource_handler.setWelcomeFiles(Array("index.html"))
        resource_handler.setResourceBase(baseDir)

        server.setHandler(resource_handler)
        server.setHandler(context)

        server.start()
        server.join()
    }
}
