package fr.upem.mower
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class PointSpec extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "create Point" should "creation of one point" in {
    val p = Point(5, 7)
    assert(5 == p.x)
    assert(7 == p.y)
  }

  "move Point" should "return (0,0)" in {
      val p = Point(5,7)
      assert (Point(0,0) == Point.movePoint(p,-5,-7))
  }

  "move serveral times Point" should "multi move return (0,0)" in {
    val p = Point(5,7)
    assert (Point(0,0) != Point.movePoint(p,0,-7))
    assert (Point(0,0) == Point.movePoint(Point.movePoint(p,0,-7),-5,0))
  }
}
