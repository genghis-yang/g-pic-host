package info.genghis.pichost.config

import cats.effect.Sync

trait StringParser[M[_], A] {
  def parse(value: String): M[A]
}

trait StringParserInstances {

  implicit def intStringParser[M[_]: Sync]: StringParser[M, Int] =
    new StringParser[M, Int] {
      def parse(value: String): M[Int] = Sync[M].delay(value.toInt)
    }
  implicit def douleStringParser[M[_]: Sync]: StringParser[M, Double] =
    new StringParser[M, Double] {
      def parse(value: String): M[Double] = Sync[M].delay(value.toDouble)
    }
  implicit def stringStringParser[M[_]: Sync]: StringParser[M, String] =
    new StringParser[M, String] {
      def parse(value: String): M[String] = Sync[M].pure(value)
    }
  implicit def longStringParser[M[_]: Sync]: StringParser[M, Long] =
    new StringParser[M, Long] {
      def parse(value: String): M[Long] = Sync[M].delay(value.toLong)
    }
}

object StringParser extends StringParserInstances {
  def apply[M[_], A](implicit parser: StringParser[M, A]): StringParser[M, A] =
    parser
}
