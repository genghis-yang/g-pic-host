package info.genghis.pichost.config

final case class AppConfig(
    username: String,
    accessToken: String
)

trait AppConfigLoader[M[_]] {
  def load: M[AppConfig]
}
