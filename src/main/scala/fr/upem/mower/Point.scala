package fr.upem.mower

final case class Point(x: Int, y: Int)

object Point {

  //move a point p
  def movePoint (p:Point, deltaX: Int, deltaY: Int):Point = p.copy(x = p.x + deltaX, y = p.y + deltaY)
}