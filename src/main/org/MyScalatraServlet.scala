package org

import org.scalatra.ScalatraServlet

/**
 * Created with IntelliJ IDEA.
 * User: alexanderdomeshek
 * Date: 5/30/13
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
class MyScalatraServlet extends ScalatraServlet{

    get("/"){
        "hello, world"
    }

}
