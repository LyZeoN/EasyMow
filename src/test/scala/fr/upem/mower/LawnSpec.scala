package fr.upem.mower

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class LawnSpec extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "createLawn" should "create lawn" in {
    val g = Lawn(5,5,List.empty[Point])
    assert(5 == g.x)
    assert(5 == g.y)
    assert( List.empty[Point] == g.list)
  }

  "addMower" should "add mower to lawn" in {
    val g = Lawn(5,5,List.empty[Point])
    assert(List(Point(3,3)) == Lawn.addMower(g,Point(3,3)).list)
  }

  "multipleMower" should "add multiple mower to lawn" in {
    val g = Lawn(5,5,List.empty[Point])
    assert(List(Point(3,3),Point(4,4)) == Lawn.addMower(Lawn.addMower(g,Point(3,3)),Point(4,4)).list)
  }

  "sameMower" should "add same mower to lawn" in {
    val g = Lawn(5,5,List.empty[Point])
    assert(List(Point(3,3)) == Lawn.addMower(Lawn.addMower(g,Point(3,3)),Point(3,3)).list)
  }

  "isOnMower" should "is on mower" in {
    val g = Lawn(5,5,List.empty[Point])
    val lawn = Lawn.addMower(Lawn.addMower(g,Point(3,3)),Point(4,4))
    assert(Lawn.isOnMower(lawn,3,3))
    assert(Lawn.isOnMower(lawn,4,4))
  }

  "notOnMower" should "not on mower" in {
    val g = Lawn(5,5,List.empty[Point])
    val lawn = Lawn.addMower(Lawn.addMower(g,Point(3,3)),Point(4,4))
    assert(!Lawn.isOnMower(lawn,4,3))
    assert(!Lawn.isOnMower(lawn,4,5))
  }

  "noMower" should "no mower in lawn" in {
    val g = Lawn(5,5,List.empty[Point])
    assert(!Lawn.isOnMower(g,4,3))
  }

  "isInLawn" should "is in Lawn" in {
    val lawn = Lawn(5,5,List.empty[Point])
    assert(Lawn.isInLawn(lawn,3,3))
    assert(Lawn.isInLawn(lawn,5,5))
  }

  "notInLawn" should "not in Lawn" in {
    val lawn = Lawn(5,5,List.empty[Point])
    assert(!Lawn.isInLawn(lawn,6,6))
  }


}
