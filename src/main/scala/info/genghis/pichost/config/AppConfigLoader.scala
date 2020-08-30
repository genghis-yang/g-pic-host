package info.genghis.pichost.config

import cats.effect.Sync

trait AppConfigLoader[M[_]] {
  override def load(envMap: Map[String, String]): M[AppConfig]
}

object AppConfigLoader {
  implicit object appConfigLoader extends AppConfigLoader[Sync] {
    override def load(envMap: Map[String, String]): Sync[AppConfig] = ???
  }
}