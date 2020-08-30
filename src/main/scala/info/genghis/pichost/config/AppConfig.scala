package info.genghis.pichost.config

import cats.effect.Sync
import cats.implicits._
import info.genghis.pichost.config.StringParser._

final case class AppConfig(
    username: String,
    accessToken: String
)

object AppConfig {
  def load[M[_]: Sync](environment: Environment[M]) = (
    environment.env[String]("username"),
    environment.env[String]("accessToken")
  ) mapN (AppConfig.apply(_, _))
}
