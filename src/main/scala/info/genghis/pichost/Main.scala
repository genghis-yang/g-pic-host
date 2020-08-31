package info.genghis.pichost

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import info.genghis.pichost.config.{AppConfig, AppConfigLoader, Environment}
import info.genghis.pichost.server.PicHostServer

object Main extends IOApp {
  implicit object appConfigLoader extends AppConfigLoader[IO] {
    override def load: IO[AppConfig] = {
      val environment = new Environment[IO](sys.env)
      (
        environment.env[String]("USER_NAME"),
        environment.env[String]("ACCESS_TOKEN")
        ).mapN(AppConfig.apply)
    }
  }

  def run(args: List[String]) =
    PicHostServer.stream[IO].compile.drain.as(ExitCode.Success)
}
