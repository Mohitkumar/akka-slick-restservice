package com.example

/**
  * Created by user on 3/24/2016.
  */
import slick.jdbc.MySQLProfile.api._

trait DbConnection {
  val db = Database.forConfig("mysql")
}
