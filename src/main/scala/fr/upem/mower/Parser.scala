package fr.upem.mower

import fr.upem.mower.Orientation.{E, N, S, W}

import scala.io.Source

class Parser (path : String){
  val lines :Iterator[String] = Source.fromFile(path).getLines()

  def parse(): Boolean ={
    val line0 = lines.next()
    val regex = "(\\d+) (\\d+)".r
    line0 match {
      case regex(x,y) =>
        val lawn:Lawn = Lawn(x.toInt,y.toInt,List.empty[Point])
        if (parseMower(lawn,2)){
          return true
        }
        false
      case _ =>
        println("Error syntax line 1")
        false
    }
  }

  def parseMower(lawn: Lawn, a:Int) :Boolean ={
    val line = lines.next()
    val regex = "(\\d+) (\\d+) ([N|E|W|S])".r
    line match {
      case regex(x, y, orientation) =>
        val point:Point = Point(x.toInt, y.toInt)
        if (Lawn.isInLawn(lawn,point.x,point.y) && !Lawn.isOnMower(lawn,point.x,point.y)) {
          if (parseCommand(lawn, orientation, point, a)){
            return true
          }
          false
        }else{
          println(s"Mower line $a not inside lawn")
          false
        }
      case _ =>
        println(s"Error syntax line $a")
        false
    }
  }

  def parseCommand(lawn: Lawn, orientation:String, point: Point, a: Int) :Boolean = {
    orientation match {
      case "N" =>
        val mower:Option[Mower] = Mower.run(Mower(N, point), lines.next(), lawn,a+1)
        if (nextMower(lawn,mower,a)){
          return true
        }
        false
      case "E" =>
        val mower:Option[Mower] = Mower.run(Mower(E, point), lines.next(), lawn,a+1)
        nextMower(lawn,mower,a)
        true
      case "W" =>
        val mower:Option[Mower] = Mower.run(Mower(W, point), lines.next(), lawn,a+1)
        nextMower(lawn,mower,a)
        true
      case "S" =>
        val mower:Option[Mower] = Mower.run(Mower(S, point), lines.next(), lawn,a+1)
        nextMower(lawn,mower,a)
        true
      case _ =>
        println("Error Orientation")
        false
    }
  }

  def nextMower(lawn: Lawn, mower: Option[Mower], a : Int) :Boolean = {
    mower match {
      case Some(t) =>
        if (lines.hasNext) {
          parseMower(Lawn.addMower(lawn, t.position),a+2)
        }else{
          false
        }
      case _ =>
        println("Error run mower")
        false
    }
  }
}
