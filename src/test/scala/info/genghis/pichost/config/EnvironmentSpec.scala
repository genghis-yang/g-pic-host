package info.genghis.pichost.config

import cats.effect.IO
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class EnvironmentSpec extends Specification {
  "Environment" >> {
    "get string value from environment" >> new EnvironmentSpecScope {
      environment.env[String]("StringKey").unsafeRunSync() must_== "StringValue"
    }

    "get integer value from environment" >> new EnvironmentSpecScope {
      environment.env[Int]("IntegerKey").unsafeRunSync() must_== 1
    }
  }
}

trait EnvironmentSpecScope extends Scope {
  val envMap: Map[String, String] = Map("StringKey" -> "StringValue", "IntegerKey" -> "1")
  val environment: Environment[IO] = new Environment[IO](envMap)
}
