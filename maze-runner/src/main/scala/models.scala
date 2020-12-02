
import cats._
import cats.data._
import cats.implicits._

object models {

  sealed trait Objective

  case object PATH      extends Objective
  case object OBSTACLE  extends Objective
  case object EMPTY     extends Objective

  implicit val showObjective = Show.show[Objective] {
    _ match {
      case PATH     => " "
      case OBSTACLE => "X"
      case EMPTY    => ""
    }
  }

  case class Map(objectives: Nested[List, List, Objective]) 

  implicit val showMap = Show.show[Map] { case Map(map) =>
    Foldable[Nested[List, List, *]]
      .foldLeft(map, "")(_.show combine _.show)
  }

  type Position = (Int, Int)

}