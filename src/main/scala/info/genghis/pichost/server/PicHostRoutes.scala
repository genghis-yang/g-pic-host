package info.genghis.pichost.server

import cats.effect.Sync
import cats.implicits._
import fs2.Stream
import info.genghis.pichost.service.PicService
import org.http4s.dsl.Http4sDsl
import org.http4s.{HttpRoutes, Response}

object PicHostRoutes {
  def picRoutes[M[_]: Sync](picService: PicService[M]): HttpRoutes[M] = {
    val dsl = new Http4sDsl[M] {}
    import dsl._
    HttpRoutes.of[M] { case GET -> Root / "pictures" / repo / identity =>
      for {
        contentBytes <- picService.getPic(repo, identity)
      } yield Response(body = Stream.chunk(contentBytes))
    }
  }
}
