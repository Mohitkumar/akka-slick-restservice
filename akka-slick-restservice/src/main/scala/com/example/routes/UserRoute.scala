package com.example.routes

/**
  * Created by user on 1/28/2017.
  */

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import com.example.Protocol
import com.example.dao.UserDao
import com.example.entity.EntityObjects.User
import spray.json._
import scala.concurrent.ExecutionContext.Implicits.global

trait UserRoute extends Protocol{
  val userApi = (path("users") & get ) {
    complete (UserDao.findAll.map(_.toJson))
  }~
    (path("users"/IntNumber) & get) { id =>
      val user = UserDao.findById(id);
      onSuccess(user){
        case Some(u) => complete(u)
        case None => complete(Accepted,"user not found")
      }
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
