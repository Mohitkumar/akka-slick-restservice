package com.example

import com.typesafe.config.ConfigFactory

/**
  * Created by user on 1/28/2017.
  */
trait Config {
  val config = ConfigFactory.load
  val root = config.getString("application.root")
  val host = config.getString("http.host")
  val port = config.getInt("http.port")
  val db = config.getConfig("mysql")
  val dbUrl = db.getString("url")
  val dbUser = db.getString("user")
  val dbPassword = db.getString("password")
}
