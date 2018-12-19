package fr.upem.main

import fr.upem.parser.Parser
///home/jordan/Bureau/tondeuse.txt
object Main extends App{
  println("Entrer le chemin du fichier a parser : ")
  val input = scala.io.StdIn.readLine()
  val parser = new Parser(input)
  parser.parse();
}
