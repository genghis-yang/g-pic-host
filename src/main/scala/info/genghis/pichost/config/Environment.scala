package info.genghis.pichost.config

import cats.effect.Sync
import info.genghis.pichost.error.AppConfigError

class Environment[M[_]: Sync](envMap: Map[String, String]) {

  def env[A](key: String)(implicit evidence: StringParser[M, A]): M[A] = {
    envMap.get(key) match {
      case None => Sync[M].raiseError(AppConfigError(List(key)))
      case Some(value) => evidence.parse(value)
    }
  }
}
