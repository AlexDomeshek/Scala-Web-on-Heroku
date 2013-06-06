package org.model

import org.squeryl.Schema
import org.squeryl.annotations.Column
import java.util.Date
import java.sql.Timestamp



/**
 * CREATE TABLE `AUTHORS` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL DEFAULT \'\',
  `lastName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 * */

case class Author(id: Long,
             firstName: String,
             lastName: String,
             email: Option[String]) {
    def this() = this(0,"","",Some(""))
}

// fields can be mutable or immutable

class Book(val id: Long,
           var title: String,
           @Column("AUTHOR_ID") // the default 'exact match' policy can be overriden
           var authorId: Long,
           var coAuthorId: Option[Long]) {

    def this() = this(0,"",0,Some(0L))
}

class Borrowal(val id: Long,
               val bookId: Long,
               val borrowerAccountId: Long,
               val scheduledToReturnOn: Date,
               val returnedOn: Option[Timestamp],
               val numberOfPhonecallsForNonReturn: Int)

object Library extends Schema {

    //When the table name doesn't match the class name, it is specified here :
    val authors = table[Author]("AUTHORS")

    val books = table[Book]

    val borrowals = table[Borrowal]
}
