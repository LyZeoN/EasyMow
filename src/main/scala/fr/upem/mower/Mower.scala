package fr.upem.mower

import fr.upem.mower.Orientation.{N,E,W,S}
import ShowMower.Show


final case class Mower(orientation:Orientation, position: Point)
object Mower{

  //to show a mower
  implicit val MowerShow  = new Show[Mower] {
    def show(t: Mower):String =
      t.orientation match {
        case N => s"X :${t.position.x} Y :${t.position.y} N"
        case E => s"X :${t.position.x} Y :${t.position.y} E"
        case W => s"X :${t.position.x} Y :${t.position.y} W"
        case S => s"X :${t.position.x} Y :${t.position.y} S"
        case _ => ""
      }
  }

  //run a mower on a lawn with these commands
  def run (t: Mower, command: String, lawn:Lawn, line:Int) :Option[Mower] = {
    command.length match {
      case l if l >0 =>
        command(0) match {
          case 'D' => changeDirection(t,command,lawn,line,Orientation.rotateD)
          case 'G' => changeDirection(t,command,lawn,line,Orientation.rotateG)
          case 'A' =>
            t.orientation match {
              case N => progress(t,command,lawn,line,0,1)
              case E => progress(t,command,lawn,line,1,0)
              case W => progress(t,command,lawn,line,-1,0)
              case S => progress(t,command,lawn,line,0,-1)
              case _ => None
            }
          case _ =>
            println(s"Error syntax line $line")
            None
        }
      case _ =>
        println(ShowMower.show(t))
        Some(t)
    }
  }

  //change the orientation of a mower with the function in argument and consum one command
  def changeDirection(t :Mower, command :String, lawn:Lawn, line :Int, f : Orientation => Option[Orientation]) :Option[Mower] = {
    f(t.orientation) match {
      case Some(o) => run(t.copy(o, t.position), command.substring(1), lawn, line)
      case _ =>
        println(s"Error rotate line $line")
        None
    }
  }

  //move the mower on the lawn and consum one command
  def progress (t :Mower, command :String, lawn :Lawn, line :Int, deltaX :Int, deltaY :Int) :Option[Mower] = {
    if (Lawn.isInLawn(lawn,t.position.x+deltaX,t.position.y+deltaY) && !Lawn.isOnMower(lawn,t.position.x+deltaX,t.position.y+deltaY)) {
      run(t.copy(position = Point.movePoint(t.position, deltaX, deltaY)), command.substring(1), lawn, line)
    }else{
      println(ShowMower.show(t))
      Some(t)
    }
  }
}