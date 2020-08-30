package info.genghis.pichost.server

import cats.effect.Sync
import cats.implicits._
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.`Content-Type`
import org.http4s.HttpRoutes
import org.http4s.MediaType
import info.genghis.pichost.{HelloWorld, Jokes}

import info.genghis.pichost.service.PicService

object PicHostRoutes {
  def picRoutes[M[_]: Sync](picService: PicService[M]): HttpRoutes[M] = {
    val dsl = new Http4sDsl[M] {}
    import dsl._
    HttpRoutes.of[M] {
      case GET -> Root / "pictures" / UUIDVar(identity) =>
        for {
          joke <- Sync[M].pure(())
          resp <- Ok(joke).map(
            _.withContentType(`Content-Type`(MediaType.image.png))
          ) // TODO Set media type automatically
        } yield resp
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
