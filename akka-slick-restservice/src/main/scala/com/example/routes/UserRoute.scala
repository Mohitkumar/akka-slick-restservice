package com.example.routes

/**
  * Created by user on 1/28/2017.
  */

import akka.http.scaladsl.server.Directives._
import com.example.Protocol
import com.example.dao.UserDao
import com.example.entity.EntityObjects.User
import spray.json._
import scala.concurrent.ExecutionContext.Implicits.global
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
trait UserRoute extends Protocol{
  val userApi = (path("users") & get ) {
    complete (UserDao.findAll.map(_.toJson))
  }~
    (path("users"/IntNumber) & get) { id =>
      complete (UserDao.findById(id).map(_.toJson))
    }~
    (path("users") & post) { entity(as[User]) { user =>
      complete (UserDao.insertReturning(user).map(_.toJson))
    }
    }~
    (path("users"/IntNumber) & put) { id => entity(as[User]) { user =>
      complete (UserDao.update(user).map(_.toJson))
    }
    }
}
