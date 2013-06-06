package org

import org.squeryl.{Session, SessionFactory}
import org.squeryl.adapters.MySQLAdapter
import org.squeryl.PrimitiveTypeMode._
import scala.Some
import org.model.{Author, Library}

object SQLManager extends  App{
    def init() {

        Class.forName("com.mysql.jdbc.Driver")

        SessionFactory.concreteFactory = Some(()=>
            Session.create(
                java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root", ""),
                new MySQLAdapter))
    }
    init()

    transaction{
        Library.authors.where(a => a.id === 1 ).toString()
    }

}