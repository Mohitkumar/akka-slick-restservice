package com.example

import java.sql.Timestamp

import com.example.entity.EntityObjects.UserSourceEnum._
import com.example.entity.EntityObjects.{UserSourceEnum, User}
import spray.json._

/**
  * Created by user on 1/28/2017.
  */
trait Protocol extends DefaultJsonProtocol{
  implicit object UserSourceEnumFormat extends RootJsonFormat[UserSourceEnum]{
    override def read(json: JsValue): UserSourceEnum = {
      json match {
        case JsString(txt) => UserSourceEnum.withName(txt)
        case something => throw new DeserializationException(s"Expected a value from enum $UserSourceEnum instead of $something")
      }
    }

    override def write(obj: UserSourceEnum): JsValue = {
      JsString(obj.toString)
    }
  }
  implicit object DateFormat extends RootJsonFormat[Timestamp]{
    override def read(json: JsValue): Timestamp ={
      json match {
        case JsString(txt) => Timestamp.valueOf(txt)
        case something =>   throw new DeserializationException(s"can not convert to date $something")
      }
    }

    override def write(obj: Timestamp): JsValue = {
      JsString(obj.toString)
    }
  }
  implicit val userFormat = jsonFormat9(User)
}
