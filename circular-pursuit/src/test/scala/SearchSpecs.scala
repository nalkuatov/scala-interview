
import org.scalacheck.{Properties, Gen}
import org.scalacheck.Prop.forAll

object SearchSpecs extends Properties("Search") {

  val uniqueSortedInts: Gen[List[Int]] =
    Gen.containerOf[Set, Int](Gen.posNum[Int])
      .map(_.toList)
      .map(_.sorted)
      .map(_.shuffle)

  implicit class ListOps(list: List[Int]) {

    /** Make a "circular array"
     * 
     * Let's replace (swap) the right half
     * of a sorted list with its left
     * half so that the elements take the
     * following form:
     * 
     * E.g:
     *   [1, 2, 3, 4, 5]      becomes [3, 4, 5, 1, 2]
     *   [10, 22, 30, 44, 50] becomes [30, 44, 50, 10, 22]
     * 
     */
    def shuffle: List[Int] = 
      list.slice(list.length / 2, list.length) :::
      list.slice(0, list.length / 2)
  }

  /** A sample unit test on `Search.search(...)`
   * 
   * Let's examine the correctness 
   * of our search implementation 
   * against the standart library's 
   * `indexOf` function (which is assumed to be 
   * tested, well implemented and correct)
   * 
   */
  property("search") = 
    /**
     * `uniqueSortedInts` - Custom circular list generator defined above
     * `Gen.posNum[Int]`  - Scalacheck's standard postivie integer generator
     */
    forAll(uniqueSortedInts, Gen.posNum[Int]){ (list: List[Int], target: Int) =>
      import Search._
      search(list, target) == list.indexOf(target)
    }

}
