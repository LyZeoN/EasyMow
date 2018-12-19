package fr.upem.parser
import fr.upem.gazon.Gazon
import fr.upem.point.Point
import fr.upem.tondeuse._
import fr.upem.orientation.Orientation.{E, N, S, W}

import scala.io.Source

class Parser (path : String){
  val file = Source.fromFile(path)
  val lines = file.getLines()

  def parse(): Unit ={
    val line0 = lines.next()
    val regex = "(\\d+) (\\d+)".r
    line0 match {
      case regex(x,y) => {
        val gazon:Gazon = Gazon(x.toInt,y.toInt,List.empty[Point])
        parseTondeuse(gazon,2)
      }
      case _ => println("Error syntax line 1")
    }
  }

  def parseTondeuse(gazon: Gazon,a:Int): Unit ={
    val line = lines.next()
    val regex = "(\\d+) (\\d+) ([N|E|W|S])".r
    line match {
      case regex(x, y, orientation) => {
        val point:Point = Point(x.toInt, y.toInt)
        Gazon.isInGazon(gazon,point.x,point.y) match {
          case true =>{
            orientation match {
              case "N" => {
                val tondeuse:Option[Tondeuse] = Tondeuse.run(Tondeuse(N, point), lines.next(), gazon,a+1)
                tondeuse match {
                  case Some(t) => if (lines.hasNext) {parseTondeuse(Gazon.addTondeuse(gazon, t.position),a+2)}
                  case _ => println("Error run tondeuse")
                }
              }
              case "E" => {
                val tondeuse:Option[Tondeuse] = Tondeuse.run(Tondeuse(E, point), lines.next(), gazon,a+1)
                tondeuse match {
                  case Some(t) => if (lines.hasNext) {parseTondeuse(Gazon.addTondeuse(gazon, t.position),a+2)}
                  case _ => println("Error run tondeuse")
                }
              }
              case "W" => {
                val tondeuse:Option[Tondeuse] = Tondeuse.run(Tondeuse(W, point), lines.next(), gazon,a+1)
                tondeuse match {
                  case Some(t) => if (lines.hasNext) {parseTondeuse(Gazon.addTondeuse(gazon, t.position),a+2)}
                  case _ => println("Error run tondeuse")
                }
              }
              case "S" => {
                val tondeuse:Option[Tondeuse] = Tondeuse.run(Tondeuse(S, point), lines.next(), gazon,a+1)
                tondeuse match {
                  case Some(t) => if (lines.hasNext) {parseTondeuse(Gazon.addTondeuse(gazon, t.position),a+2)}
                  case _ => println("Error run tondeuse")
                }
              }
              case _ => println("Error Orientation")
            }
          }
          case false =>  println(s"La tondeuse line ${a} n'est pas dans le gazon")
        }
      }
      case _ => println(s"Error syntax line ${a}")

    }
  }
}
