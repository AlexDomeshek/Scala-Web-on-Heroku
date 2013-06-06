package org

import javax.servlet.{ServletContextEvent, ServletContextListener}
import org.util.Logging

class AppListener extends ServletContextListener with Logging{
    def  contextInitialized( sce: ServletContextEvent){
        info("Starting up the app!!")
        SQLManager.init()
    }

    def contextDestroyed(sce:ServletContextEvent){
        info("Bringing down the app!!")
    }
}
