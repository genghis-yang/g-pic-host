package info.genghis.pichost.service

import io.circe._
import io.circe.generic.semiauto._

case class GithubResponse(
  name: String,
  path: String,
  sha: String,
  size: Long,
  url: String,
  htmlUrl: String,
  gitUrl: String,
  downloadUrl: String,
  resType: String,
  content: String,
  encoding: String,
  links: GithubLinks
)

case class GithubLinks(self: String, git: String, html: String)

object GithubResponse {
  implicit val githubResponseDecoder: Decoder[GithubResponse] =
    Decoder.forProduct12("name", "path", "sha", "size", "url", "html_url", "git_url", "download_url", "type", "content", "encoding", "_links")(
      GithubResponse.apply
    )
}

object GithubLinks {
  implicit val githubLinksDecoder: Decoder[GithubLinks] = deriveDecoder
}
