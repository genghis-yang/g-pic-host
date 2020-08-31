package info.genghis.pichost.service

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

case class GithubResponse(
  name: String,
  path: String,
  sha: String,
  size: Long,
  url: String,
  html_url: String,
  git_url: String,
  download_url: String,
  `type`: String,
  content: String,
  encoding: String,
  _links: GithubLinks
)

case class GithubLinks(self: String, git: String, html: String)

object GithubResponse {
  implicit val githubResponseDecoder: Decoder[GithubResponse] = deriveDecoder
}

object GithubLinks {
  implicit val githubLinksDecoder: Decoder[GithubLinks] = deriveDecoder
}
