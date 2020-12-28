package DesignPatterns

import DesignPatterns.facade.BasicOperationHandler
import DesignPatterns.singleton.CarNotFoundException

import scala.io.StdIn

object CarApplication {

  private object Commands {
    val CREATE = "create"
    val DELETE = "delete"
    val PRINT = "print"
    val HELP = "help"
    val EXIT = "exit"
  }

  private object Cars {
    val KABRIOLET = "kabriolet"
    val KOMBI = "kombi"
    val MINIVAN = "minivan"
    val SEDAN = "sedan"
  }

  private object PrintOptions {
    val CARS = "cars"
    val SUBCONTRACTORS = "subcontractors"
  }

  private val operationHandler = new BasicOperationHandler

  private def handleCreate(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("COMMAND MUST HAVE 1 ARG")
    } else {
      var isUnknown = false
      args.head match {
        case Cars.KABRIOLET => operationHandler.createKabriolet()
        case Cars.KOMBI => operationHandler.createKombi()
        case Cars.MINIVAN => operationHandler.createMinivan()
        case Cars.SEDAN => operationHandler.createSedan()
        case other =>
          isUnknown = true
          println("UNKNOWN CAR TYPE " + other)
      }
      if (!isUnknown) {
        println(args.head.toUpperCase + " CREATED")
      }
    }
  }

  def handleDelete(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("COMMAND MUST HAVE 1 ARG")
    } else {
      try {
        operationHandler.deleteCar(args.head.toInt)
        println("CAR " + args.head + " WAS SUCCESSFULLY DELETED")
      } catch {
        case e: CarNotFoundException => println("CAR WITH GIVEN SERIALNUMBER NOT FOUND")
      }
    }
  }

  def handlePrint(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("COMMAND MUST HAVE 1 ARG")
    } else {
      args.head match {
        case PrintOptions.CARS => operationHandler.printCreatedCars()
        case PrintOptions.SUBCONTRACTORS => operationHandler.printSubContractorTree()
        case other => println("UNKNOWN PRINT TYPE " + other)
      }
    }
  }

  def handleHelp(): Unit = {
    println("Usage: \n\tcreate\n\t\tkabriolet\n\t\tkombi\n\t\tminivan\n\t\tsedan\n\t" +
      "delete <serial number>\n\tprint\n\t\tcars\n\t\tsubcontractors\n\thelp\n\texit")
  }

  def handleExit(): Unit = {
    sys.exit(0)
  }

  def main(args: Array[String]): Unit = {

    handleCreate(Array("kombi"))
    handleCreate(Array("kombi"))
    handleCreate(Array("kombi"))
    handleCreate(Array("kombi"))
    handlePrint(Array("subcontractors"))

    while (true) {
      val command = StdIn.readLine().split(" ")
      command.head match {
        case Commands.CREATE => handleCreate(command.tail)
        case Commands.DELETE => handleDelete(command.tail)
        case Commands.PRINT => handlePrint(command.tail)
        case Commands.HELP => handleHelp()
        case Commands.EXIT => handleExit()
        case c => println("UNKNOWN COMMAND " + c)
      }
    }
  }
}
