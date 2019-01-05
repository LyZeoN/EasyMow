package fr.upem.mower

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class MowerSpec extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks{

  "createMower" should "create Mower" in {
    val mower = Mower(Orientation.N,Point(3,3))
    assert(Point(3,3) == mower.position)
    assert(Orientation.N == mower.orientation)
  }

  "runMower" should "run Mower" in {
    val mower = Mower(Orientation.N,Point(1,2))
    val g = Lawn(5,5,List.empty[Point])
    val endmower = Mower.run(mower,"GAGAGAGAA", g,2).get
    assert(Point(1,3) == endmower.position)
    assert(Orientation.N == endmower.orientation)
  }

  "runMowerError" should "run Mower Error" in {
    val mower = Mower(Orientation.N,Point(1,2))
    val g = Lawn(5,5,List.empty[Point])
    val endmower = Mower.run(mower,"GAGAEGAGAA", g,2)
    assert(endmower.isEmpty)
  }

  "changeDirectionD" should "change orientation mower rotateD" in {
    val mower = Mower(Orientation.N,Point(1,2))
    val g = Lawn(5,5,List.empty[Point])
    val endmower = Mower.changeDirection(mower,"D",g,2,Orientation.rotateD).get
    assert(Orientation.E == endmower.orientation)
    assert(Point(1,2) == endmower.position)
  }

  "changeDirectionG" should "change orientation mower rotateG" in {
    val mower = Mower(Orientation.N,Point(1,2))
    val g = Lawn(5,5,List.empty[Point])
    val endmower = Mower.changeDirection(mower,"G",g,2,Orientation.rotateG).get
    assert(Orientation.W == endmower.orientation)
    assert(Point(1,2) == endmower.position)
  }

  "progress" should "progress mower" in {
    val mower = Mower(Orientation.N,Point(1,2))
    val g = Lawn(5,5,List.empty[Point])
    val endmower = Mower.progress(mower,"A",g,2,0,1).get
    assert(Orientation.N == endmower.orientation)
    assert(Point(1,3) == endmower.position)
  }

  "progress not in lawn" should "progress mower not in lawn" in {
    val mower = Mower(Orientation.N,Point(1,2))
    val g = Lawn(2,2,List.empty[Point])
    val endmower = Mower.progress(mower,"A",g,2,0,1).get
    assert(Orientation.N == endmower.orientation)
    assert(Point(1,2) == endmower.position)
  }

  "progress on another mower" should "progress on another mower in lawn" in {
    val mower = Mower(Orientation.N,Point(1,1))
    val g = Lawn(2,2,List(Point(1,2)))
    val endmower = Mower.progress(mower,"ADA",g,2,0,1).get
    assert(Orientation.N == endmower.orientation)
    assert(Point(1,1) == endmower.position)
  }

  "showMower" should "show a mower" in {
    val mower = Mower(Orientation.N,Point(1,1))
    assert("X :1 Y :1 N".equals(ShowMower.show(mower)))
  }

}
