package info.genghis.pichost.service

import fs2.Chunk
import org.http4s.client.Client
import org.http4s.EntityDecoder.binary
import org.http4s._
import org.http4s.implicits._
import org.http4s.headers.`Content-Type`

trait PicService[M[_]] {
  def getPic(repo: String, identity: String): M[Chunk[Byte]]
}

object PicService {
  def apply[M[_]](httpClient: Client[M]): PicService[M] = new PicService[M] {
    override def getPic(repo: String, identity: String): M[Chunk[Byte]] = for {
      content <- httpClient.expect[Chunk[Byte]](
        Request[M](
          uri = uri"/repos/$owner/$repo/contents/$identity",
          headers = Headers(List(Header(`Content-Type`.name.value, "application/vnd.github.VERSION.raw")))
        )
      )
    } yield content
  }
}