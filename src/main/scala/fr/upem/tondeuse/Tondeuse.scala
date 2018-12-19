package fr.upem.tondeuse

import fr.upem.gazon.Gazon
import fr.upem.orientation.Orientation
import fr.upem.orientation.Orientation._
import fr.upem.point.Point
import fr.upem.show.ShowTondeuse

final case class Tondeuse(orientation:Orientation, position: Point)
object Tondeuse{
  def run ( t: Tondeuse,command: String,gazon:Gazon,line:Int) :Option[Tondeuse] = {
      command.length match {
        case l if l >0 => {
          command(0) match {
            case 'D' => {
              Orientation.rotateD(t.orientation) match {
                case Some(o) => run(t.copy(o, t.position), command.tail,gazon,line)
                case _ => {
                  println(s"Error rotateD line ${line}")
                  None
                }
              }
            }
            case 'G' => {
              Orientation.rotateG(t.orientation) match {
                case Some(o) => run(t.copy(o, t.position), command.tail,gazon,line)
                case _ => {
                  println(s"Error rotateG line ${line}")
                  None
                }
              }
            }
            case 'A' =>
              t.orientation match {
                    case N => if (Gazon.isInGazon(gazon,t.position.x,t.position.y+1) && !Gazon.isOnTondeuse(gazon,t.position.x,t.position.y+1)){run(t.copy(position = Point.movePoint(t.position, 0, 1)), command.tail, gazon,line)}else{println(ShowTondeuse.show(t));Some(t)}
                    case E => if (Gazon.isInGazon(gazon,t.position.x+1,t.position.y) && !Gazon.isOnTondeuse(gazon,t.position.x+1,t.position.y)){run(t.copy(position= Point.movePoint(t.position, 1, 0)), command.tail, gazon,line)}else{println(ShowTondeuse.show(t));Some(t)}
                    case W => if (Gazon.isInGazon(gazon,t.position.x-1,t.position.y) && !Gazon.isOnTondeuse(gazon,t.position.x-1,t.position.y)){run(t.copy(position = Point.movePoint(t.position, -1, 0)), command.tail, gazon,line)}else{println(ShowTondeuse.show(t));Some(t)}
                    case S => if (Gazon.isInGazon(gazon,t.position.x,t.position.y-1) && !Gazon.isOnTondeuse(gazon,t.position.x,t.position.y-1)){run(t.copy(position = Point.movePoint(t.position, 0, -1)), command.tail, gazon,line)}else{println(ShowTondeuse.show(t));Some(t)}
                    case _ => None
              }
            case _ => {
              println(s"Error syntax line ${line}")
              None
            }
          }
        }
        case _ => {
          println(ShowTondeuse.show(t))
          Some(t)
        }
      }
  }

}