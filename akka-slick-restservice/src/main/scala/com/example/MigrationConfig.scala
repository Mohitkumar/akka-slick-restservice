package com.example

import com.example.entity.EntityDefinition.UserTable
import com.example.entity.EntityObjects.User
import org.slf4j.LoggerFactory

import slick.driver.MySQLDriver.api.{DBIO, Database}
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import slick.driver.MySQLDriver.api._
/**
  * Created by user on 1/28/2017.
  */
trait MigrationConfig extends Config{
  val logger = LoggerFactory.getLogger(classOf[MigrationConfig])
  val users: TableQuery[UserTable] = TableQuery[UserTable]
  val database = Database.forConfig("mysql")
  def migrate(){
    try {
      Await.result(database.run(DBIO.seq(
        MTable.getTables map (tables => {
          if (!tables.exists(_.name.name == users.baseTableRow.toString)){
            logger.info("creating user table")
            Await.result(database.run(users.schema.create), Duration.Inf)
          }
        })
      )), Duration.Inf)
    }
    finally {
      database.close()
    }
}
}
