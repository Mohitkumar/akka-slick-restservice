package com.example.dao

import com.example.DbConnection
import com.example.entity.EntityDefinition.UserTable
import com.example.entity.EntityObjects.User
import slick.basic.BasicAction
import slick.dbio
import slick.dbio.Effect.{Transactional, Read, Write}
import slick.dbio.{DBIOAction, NoStream}
import slick.lifted.TableQuery
import slick.sql.{FixedSqlAction, SqlAction, FixedSqlStreamingAction}

import scala.concurrent.Future

/**
  * Created by user on 1/28/2017.
  */
trait BaseDao extends DbConnection{
  val userTable = TableQuery[UserTable]
  protected implicit def executeFromDb[A](action: BasicAction[A, NoStream, _ <: slick.dbio.Effect]): Future[A] = {
    db.run(action)
  }

  protected implicit def executeFromDbOption[A](action: BasicAction[Option[A], NoStream, _ <: slick.dbio.Effect]): Future[Option[A]] = {
    db.run(action)
  }

  protected implicit def executeReadStreamFromDb[A](action: FixedSqlStreamingAction[Seq[A], A, _ <: slick.dbio.Effect]): Future[Seq[A]] = {
    db.run(action)
  }

  protected implicit def executeWriteStreamFromDb[A](action: FixedSqlAction[A, NoStream, _ <: slick.dbio.Effect]): Future[A] = {
    db.run(action)
  }

  protected implicit def executeWriteStreamFromDbOption[A](action: DBIOAction[Option[A], NoStream, _ <: slick.dbio.Effect]): Future[Option[A]] = {
    db.run(action)
  }
}
