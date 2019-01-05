package fr.upem.mower

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class ParserSpec extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "parseLawn" should "parse return true" in {
    val parser = new Parser("./ressources/tondeuse.txt")
    assert (parser.parse())
  }

  "parseErrorCommand" should "parse with error command" in {
    val parser = new Parser("./ressources/ErrorCommand.txt")
    assert (!parser.parse())
  }

  "parseErrorSyntax" should "parse with error syntax" in {
    val parser = new Parser("./ressources/ErrorSyntax.txt")
    assert (!parser.parse())
  }

  "parseErrorSyntax2" should "parse with error syntax in mower line" in {
    val parser = new Parser("./ressources/ErrorSyntax2.txt")
    assert (!parser.parse())
  }

}
