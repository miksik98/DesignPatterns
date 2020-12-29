package DesignPatterns

import DesignPatterns.command.CommandRegistry
import DesignPatterns.command.create.{CreateKabrioletCommand, CreateKombiCommand, CreateMinivanCommand, CreateSedanCommand}
import DesignPatterns.command.delete.DeleteCarCommand
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
    val UNDO = "undo"
    val REDO = "redo"
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

  private implicit val operationHandler: BasicOperationHandler = new BasicOperationHandler

  private def handleCreate(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("COMMAND MUST HAVE 1 ARG")
    } else {
      var isUnknown = false
      args.head match {
        case Cars.KABRIOLET => CommandRegistry.executeCommand(new CreateKabrioletCommand())
        case Cars.KOMBI => CommandRegistry.executeCommand(new CreateKombiCommand())
        case Cars.MINIVAN => CommandRegistry.executeCommand(new CreateMinivanCommand())
        case Cars.SEDAN => CommandRegistry.executeCommand(new CreateSedanCommand())
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
        CommandRegistry.executeCommand(new DeleteCarCommand(args.head.toInt))
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

  def handleUndo(): Unit = {
    CommandRegistry.undo()
  }

  def handleRedo(): Unit = {
    CommandRegistry.redo()
  }

  def main(args: Array[String]): Unit = {
    while (true) {
      val command = StdIn.readLine().split(" ")
      command.head match {
        case Commands.CREATE => handleCreate(command.tail)
        case Commands.DELETE => handleDelete(command.tail)
        case Commands.PRINT => handlePrint(command.tail)
        case Commands.UNDO => handleUndo()
        case Commands.REDO => handleRedo()
        case Commands.HELP => handleHelp()
        case Commands.EXIT => handleExit()
        case c => println("UNKNOWN COMMAND " + c)
      }
    }
  }
}