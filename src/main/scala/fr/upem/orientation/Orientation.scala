package fr.upem.orientation

object Orientation{
  sealed trait Orientation
  case object N extends Orientation
  case object E extends Orientation
  case object W extends Orientation
  case object S extends Orientation

  def rotateD(orientation: Orientation) :Option[Orientation]= {
    orientation match {
      case N => Some(E)
      case E => Some(S)
      case W => Some(N)
      case S => Some(W)
      case _ => None
    }
  }

  def rotateG(orientation: Orientation) :Option[Orientation]= {
    orientation match {
      case N => Some(W)
      case E => Some(N)
      case W => Some(S)
      case S => Some(E)
      case _ => None
    }
  }
}