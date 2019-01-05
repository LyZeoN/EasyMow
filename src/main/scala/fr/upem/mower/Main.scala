package fr.upem.mower

import java.nio.file.{Files, Paths}
//./ressources/tondeuse.txt
object Main extends App{
  println("Enter the path of a file : ")
  val input = scala.io.StdIn.readLine()
  if (!Files.exists(Paths.get(input))){
    println("This file does not exist, try again.")
  }else{
    val parser = new Parser(input)
    parser.parse()
  }
}