package dissonance

import java.nio.file.Path

import cats.effect.IO
import fs2.io.file.Files

object TestUtils {
  def readFileFromResource(path: String): fs2.Stream[IO, String] = {
    Files[IO]
      .readAll(Path.of(getClass.getResource(path).toURI), 4096)
      .through(fs2.text.utf8Decode)
      .through(fs2.text.lines)
      .dropLastIf(_.isEmpty)
  }
}
