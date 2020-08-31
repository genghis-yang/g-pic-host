package info.genghis.pichost.server

import cats.effect.Sync
import cats.implicits._
import fs2.Stream
import info.genghis.pichost.config.AppConfigLoader
import info.genghis.pichost.service.PicService
import info.genghis.pichost.{HelloWorld, Jokes}
import org.http4s.dsl.Http4sDsl
import org.http4s.{HttpRoutes, Response}

object PicHostRoutes {
  def picRoutes[M[_]: Sync : AppConfigLoader](picService: PicService[M]): HttpRoutes[M] = {
    val dsl = new Http4sDsl[M] {}
    import dsl._
    HttpRoutes.of[M] {
      case GET -> Root / "pictures" / repo / identity =>
        for {
          contentBytes <- picService.getPic(repo, identity)
        } yield Response(body = Stream.chunk(contentBytes))
    }
  }

  def jokeRoutes[F[_]: Sync](J: Jokes[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "joke" =>
        for {
          joke <- J.get
          resp <- Ok(joke)
        } yield resp
    }
  }

  def helloWorldRoutes[F[_]: Sync](H: HelloWorld[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "hello" / name =>
        for {
          greeting <- H.hello(HelloWorld.Name(name))
          resp <- Ok(greeting)
        } yield resp
    }
  }
}
