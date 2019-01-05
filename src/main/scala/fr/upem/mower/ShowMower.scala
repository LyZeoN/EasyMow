package fr.upem.mower

object ShowMower {
  trait Show[T] {
    def show(t: T): String
  }

  def show[A](a: A)(implicit ev: Show[A]) :String = ev.show(a)
}
