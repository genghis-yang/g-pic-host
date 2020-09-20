package info.genghis.pichost.config

final case class AppConfig(
    username: String,
    accessToken: String,
    serverPort: Int = 8080,
    serverIp: String = "0.0.0.0"
)

trait AppConfigLoader[M[_]] {
  def load: M[AppConfig]
}
