package com.example.dao

import com.example.DbConnection
import com.example.entity.EntityObjects.User
import slick.dbio
import slick.dbio.Effect.{Transactional, Read, Write}
import slick.sql.FixedSqlAction

import scala.concurrent.{Future, Await}
import scala.concurrent.duration.Duration
import slick.driver.MySQLDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by user on 1/28/2017.
  */
object UserDao extends BaseDao{
  private def filterQuery(id: Long)= {
    userTable.filter(_.id === id)
  }
  def findById(id: Long):Future[Option[User]] = {
    executeFromDbOption(filterQuery(id).result.headOption)
  }
  def findAll():Future[Seq[User]] = {
    userTable.result
  }

  def findByEmail(email: String) = {
    userTable.filter(_.email === email).result.headOption
  }
  def findByUserNamePassword(userName: String, password:String) = {
    userTable.filter(_.userName === userName).filter(_.password === password).result.headOption
  }
  def findByUserIdPassword(userId: Long, password:String) = {
    userTable.filter(_.id === userId).filter(_.password === password).result.headOption
  }
  def findByUserName(userName: String) = {
    userTable.filter(_.userName === userName).result.headOption
  }

  def insertReturning(user: User):Future[User] = {
    (userTable returning userTable.map(_.id) into ((user,id) => user.copy(id=Some(id)))) += user
  }

  def update(user: User):Future[Option[User]] = {
    val tr = (for {
      result <- userTable.update(user)
      s <- userTable.filter(_.id === user.id).result.headOption
    } yield s).transactionally
    tr;
  }
  def updateOrInsert(user:User) = {
    val tr = (for {
      existing <- userTable.filter(_.userName === user.userName).result.headOption
      row       = existing.map(_.copy(id=user.id)) getOrElse user
      result   <- userTable.insertOrUpdate(row)
      s        <-  userTable.filter(_.userName === row.userName).result.headOption
    } yield s).transactionally
    tr;
  }

  def updateUserPassword(id:Long, password:Option[String]) = {
    val tr = (for {
      ex <- filterQuery(id).map(f => f.password).
        update(password)
      s <-  userTable.filter(_.id === id).result.headOption
    } yield s).transactionally
    tr;
  }

  def delete(id: Long) = {
    filterQuery(id).delete
  }
}
