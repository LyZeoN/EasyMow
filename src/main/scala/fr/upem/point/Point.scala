package fr.upem.point;

final case class Point(x: Int, y: Int)

object Point {
  def movePoint (p:Point, deltaX: Int, deltaY: Int):Point = p.copy(x = p.x + deltaX, y = p.y + deltaY)
}