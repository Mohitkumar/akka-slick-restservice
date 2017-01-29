package com.example

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext

/**
  * Created by user on 1/28/2017.
  */
object Server extends MigrationConfig with Config with Routes {
  implicit val system = ActorSystem("discover-service");
  protected implicit val executor: ExecutionContext = system.dispatcher;
  protected val log: LoggingAdapter = Logging(system, getClass)
  protected implicit val materializer: ActorMaterializer = ActorMaterializer()
  def main(args: Array[String]): Unit = {
    migrate()
    Http().bindAndHandle(handler = logRequestResult("log")(routes), interface = host, port = port)
  }
}
