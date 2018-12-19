package fr.upem.show

import fr.upem.orientation.Orientation.{E, N, S, W}
import fr.upem.tondeuse.Tondeuse

object ShowTondeuse {
  trait Show[T] {
    def show(t: T): String
  }
  implicit val TondeuseShow = new Show[Tondeuse] {
    def show(t: Tondeuse):String =
      t.orientation match {
        case N => s"X :${t.position.x} Y :${t.position.y} N"
        case E => s"X :${t.position.x} Y :${t.position.y} E"
        case W => s"X :${t.position.x} Y :${t.position.y} W"
        case S => s"X :${t.position.x} Y :${t.position.y} S"
        case _ => ""
      }
  }

  def show[A](a: A)(implicit ev: Show[A]) = ev.show(a)
}