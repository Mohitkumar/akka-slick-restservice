package com.example.entity

import java.sql.Timestamp

import com.example.entity.EntityObjects.UserSourceEnum.UserSourceEnum

/**
  * Created by user on 1/28/2017.
  */
object EntityObjects {
  case class User(id:Option[Long],name:String,userName:String, email:Option[String],userSource:UserSourceEnum,
                  password:Option[String],mobile:Option[String],dateCreated: Timestamp,
                  dateUpdated: Timestamp)

  object UserSourceEnum extends Enumeration{
    type UserSourceEnum = Value
    val FACEBOOK,GMAIL,CUSTOM = Value
  }

}
