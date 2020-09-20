package info.genghis.pichost.server

import cats.effect.{ConcurrentEffect, Timer}
import fs2.Stream
import info.genghis.pichost.config.AppConfigLoader
import info.genghis.pichost.service.PicService
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.Logger

import scala.concurrent.ExecutionContext.global

object PicHostServer {

  def stream[M[_]: ConcurrentEffect: AppConfigLoader](implicit T: Timer[M]): Stream[M, Nothing] = {
    for {
      appConfig <- Stream.eval(implicitly[AppConfigLoader[M]].load)
      client <- BlazeClientBuilder[M](global).stream
      picService = PicService(client)

      // Combine Service Routes into an HttpApp.
      // Can also be done via a Router if you
      // want to extract a segments not checked
      // in the underlying routes.
      httpApp = (
        PicHostRoutes.picRoutes[M](picService)
      ).orNotFound

      // With Middlewares in place
      finalHttpApp = Logger.httpApp(true, false)(httpApp)

      exitCode <- BlazeServerBuilder[M](global)
        .bindHttp(appConfig.serverPort, appConfig.serverIp)
        .withHttpApp(finalHttpApp)
        .serve
    } yield exitCode
  }.drain
}
