package com.example

/**
  * Created by user on 1/28/2017.
  */
import akka.http.scaladsl.server.Directives._
import com.example.routes.UserRoute

trait Routes extends UserRoute{
  val routes = {
    pathPrefix("v1"){
      userApi
    } ~ path("")(getFromResource("index.html"))
  }

}
