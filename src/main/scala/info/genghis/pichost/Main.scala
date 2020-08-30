package info.genghis.pichost

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import info.genghis.pichost.server.PicHostServer

object Main extends IOApp {
  def run(args: List[String]) =
    PicHostServer.stream[IO].compile.drain.as(ExitCode.Success)
}