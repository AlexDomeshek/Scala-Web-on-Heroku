package org

import org.scalatra.ScalatraServlet

class Router extends ScalatraServlet{

    get("/api/"){
        "hello, world"
    }
}
