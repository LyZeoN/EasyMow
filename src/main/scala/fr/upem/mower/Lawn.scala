package fr.upem.mower

final case class Lawn(x:Int, y:Int, list: List[Point])
object Lawn {
  //add a mower to the lawn for collision
  def addMower (g:Lawn, p:Point):Lawn = {
    if (!g.list.contains(p)) {
      g.copy(g.x,g.y,g.list.:+(p))
    }else{
      g
    }
  }

  //check if the coordinate (x,y) is on a mower in the lawn
  def isOnMower (g:Lawn, x: Int, y:Int):Boolean = g.list.contains(Point(x,y))

  //check if the coordinate (x,y) is in the lawn
  def isInLawn(g:Lawn, x:Int, y:Int):Boolean = (x,y) match {
    case (i,j) if i >= 0 && i <= g.x && j >= 0 && j <= g.y => true
    case _ => false
  }
}