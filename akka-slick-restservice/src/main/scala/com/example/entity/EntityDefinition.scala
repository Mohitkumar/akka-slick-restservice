package com.example.entity

import java.sql.Timestamp

import com.example.entity.EntityObjects.{UserSourceEnum, User}
import com.example.entity.EntityObjects.UserSourceEnum.UserSourceEnum
import slick.lifted.Tag
import slick.sql.SqlProfile.ColumnOption.SqlType
import slick.driver.MySQLDriver.api._

/**
  * Created by user on 1/28/2017.
  */
object EntityDefinition {
  implicit val sourceEnumMapper = MappedColumnType.base[UserSourceEnum, Int](
    e => e.id,
    s => UserSourceEnum.apply(s)
  )
  class UserTable(tag:Tag) extends Table[User](tag, "User"){
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name",SqlType("varchar(200)"))
    def userName = column[String]("userName", SqlType("varchar(200)"))
    def email = column[Option[String]]("email",SqlType("varchar(200)"))
    def userSource = column[UserSourceEnum]("userSource")
    def password = column[Option[String]]("password",SqlType("varchar(200)"))
    def mobile = column[Option[String]]("mobile",SqlType("varchar(200)"))
    def dateCreated = column[Timestamp]("dateCreated", SqlType("timestamp not null default now()"))
    def dateUpdated = column[Timestamp]("dateUpdated")
    def userNameidx = index("userName",userName,unique=true)
    def emailIdx = index("email",email,unique=true)
    def * = (id.?,name,userName,email,userSource,password,mobile
      ,dateCreated,dateUpdated)  <> (User.tupled, User.unapply _)
  }

}
