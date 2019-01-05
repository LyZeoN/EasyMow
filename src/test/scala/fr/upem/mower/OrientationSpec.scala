package fr.upem.mower

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class OrientationSpec extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "rotateD" should "rotateD N->E  E->S  W->N  S->W" in {
    assert (Orientation.E == Orientation.rotateD(Orientation.N).get)
    assert (Orientation.S == Orientation.rotateD(Orientation.E).get)
    assert (Orientation.N == Orientation.rotateD(Orientation.W).get)
    assert (Orientation.W == Orientation.rotateD(Orientation.S).get)
  }

  "rotateG" should "rotateG N->W  E->N  W->S  S->E" in {
    assert (Orientation.W == Orientation.rotateG(Orientation.N).get)
    assert (Orientation.N == Orientation.rotateG(Orientation.E).get)
    assert (Orientation.S == Orientation.rotateG(Orientation.W).get)
    assert (Orientation.E == Orientation.rotateG(Orientation.S).get)
  }

  "multiple rotate" should " N->E  E->S  W->N  S->W" in {
    assert (Orientation.E == Orientation.rotateD(Orientation.rotateG(Orientation.rotateD(Orientation.N).get).get).get)
  }

}
