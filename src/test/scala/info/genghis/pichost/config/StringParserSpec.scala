package info.genghis.pichost.config

import cats.effect.IO
import org.specs2.mutable.Specification

class StringParserSpec extends Specification {
  "StringParser parse string" >> {
    "to integer" >> {
      StringParser[IO, Int].parse("123").unsafeRunSync must_== 123
    }

    "to double" >> {
      StringParser[IO, Double].parse("1.23").unsafeRunSync must_== 1.23
    }

    "to string" >> {
      StringParser[IO, String].parse("123").unsafeRunSync must_== "123"
    }

    "to long" >> {
      StringParser[IO, Long].parse("123").unsafeRunSync must_== 123L
    }
  }
}
