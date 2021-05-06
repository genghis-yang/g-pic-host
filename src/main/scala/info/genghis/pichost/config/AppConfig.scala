package info.genghis.pichost.config

import cats.effect.IO
import cats.implicits._

final case class AppConfig(
  username: String,
  accessToken: String,
  serverPort: Int = 8080,
  serverIp: String = "0.0.0.0"
)

trait AppConfigLoader[M[_]] {
  def load: M[AppConfig]
}

object AppConfigLoader {
  implicit object AppConfigLoader extends AppConfigLoader[IO] {
    override def load: IO[AppConfig] = {
      val environment = new Environment[IO](sys.env)
      (
        environment.env[String]("USER_NAME"),
        environment.env[String]("ACCESS_TOKEN"),
        environment.env[Int]("SERVER_PORT"),
        environment.env[String]("SERVER_IP")
      ).mapN(AppConfig.apply)
    }
  }
}
