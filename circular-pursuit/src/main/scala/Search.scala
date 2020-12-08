

object Search {

  def search(values: List[Int], target: Int): Int =
    binarySearch(values, target)(0, values.length - 1)

  private def binarySearch(
    values: List[Int], 
    target: Int
  )(left: Int, right: Int): Int = {

    val mid = (left + right) >> 1

    if (values.isEmpty || left > right) {
      -1
    } else if (values(mid) == target) {
      mid
    } else if (target < values(mid)) {
      if (values(left) <= values(mid) && values(left) > target) 
        binarySearch(values, target)(mid + 1, right) 
      else 
        binarySearch(values, target)(left, mid - 1)
    } else {
      if (values(mid) <= values(right) && target > values(right))
        binarySearch(values, target)(left, right - 1)
      else 
        binarySearch(values, target)(mid + 1, right)
    }

  }

}
