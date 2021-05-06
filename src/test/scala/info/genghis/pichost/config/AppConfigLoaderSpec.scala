package info.genghis.pichost.config

import org.specs2.mutable.Specification

class AppConfigLoaderSpec extends Specification {
  "AppConfigLoader" >> {
    "load configurations from env" >> {
      ok
    }

    "raise an error if environment variables missing" >> {
      ok
    }

    "raise an error if environment variables has a bad format" >> {
      ok
    }
  }
}
