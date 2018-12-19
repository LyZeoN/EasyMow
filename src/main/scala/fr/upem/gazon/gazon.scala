package fr.upem.gazon

import fr.upem.point.Point

final case class Gazon(x:Int, y:Int,list: List[Point])
object Gazon {
  def addTondeuse (g:Gazon,p:Point):Gazon = g.copy(g.x,g.y,g.list.:+(p))
  def isOnTondeuse (g:Gazon, x: Int, y:Int):Boolean = g.list.contains(Point(x,y))
  def isInGazon(g:Gazon,x:Int,y:Int):Boolean = (x,y) match {
    case (x,y) if x >= 0 && x <= g.x && y >= 0 && y <= g.y => true
    case _ => false
  }
}