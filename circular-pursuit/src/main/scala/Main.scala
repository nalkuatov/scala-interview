
import cats.implicits._
import cats.effect._

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    IO { println("Hello scala world!") } *>
    ExitCode.Success.pure[IO]
  }
}
