package DesignPatterns

import DesignPatterns.command.CommandRegistry
import DesignPatterns.command.create.{CreateKabrioletCommand, CreateKombiCommand, CreateMinivanCommand, CreateSedanCommand}
import DesignPatterns.command.delete.DeleteCarCommand
import DesignPatterns.command.improve.ImproveFaultyCarsCommand
import DesignPatterns.command.snapshot.{MakeSnapshotCommand, RestoreSnapshotCommand}
import DesignPatterns.facade.BasicOperationHandler
import DesignPatterns.memento.EmptyHistoryException
import DesignPatterns.model.cars.{Car, EngineType, Kombi, QualityType}
import DesignPatterns.proxy.ForbiddenMessage
import DesignPatterns.singleton.{CarNotFoundException, InsufficientPrivileges}
import DesignPatterns.state.{Admin, Moderator}
import DesignPatterns.strategy.{SimpleImproveStrategy, SuperImproveStrategy}

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
    val IMPROVE = "improve"
    val COSTS = "costs"
    val SAVE = "save"
    val RESTORE = "restore"
    val SUDO = "sudo"
    val LOGOUT = "logout"
    val ACTIVATE = "activate"
    val SEND_MESSAGE = "SendMessage"
    val READ_MESSAGE = "ReadMessage"
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
        case e: InsufficientPrivileges => println("INSUFFICIENT PRIVILEGES. LOG IN AS ADMIN TO DELETE CAR.")
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
      "delete <serial number>\n\tprint\n\t\tcars\n\t\tsubcontractors\n\timprove\n\tcosts\n\t" +
      "save\n\trestore\n\thelp\n\tundo\n\tredo\n\texit")
  }

  def handleExit(): Unit = {
    sys.exit(0)
  }

  def handleUndo(): Unit = {
    CommandRegistry.undo()
  }

  def handleRedo(): Unit = {
    try {
      CommandRegistry.redo()
    } catch {
      case e: CarNotFoundException => println("CAR WITH GIVEN SERIALNUMBER NOT FOUND")
      case e: InsufficientPrivileges => println("INSUFFICIENT PRIVILEGES. LOG IN AS ADMIN TO DELETE CAR.")
    }
  }

  def handleImprove(args: Array[String]): Unit = {
    val strategy = {
      if (args.nonEmpty && args.head == "super") {
        SuperImproveStrategy
      } else {
        SimpleImproveStrategy
      }
    }
    CommandRegistry.executeCommand(new ImproveFaultyCarsCommand(strategy))
    println("CARS QUALITY IMPROVED")
  }

  def handleCosts(): Unit = {
    println(operationHandler.calculateCosts() + " PLN")
  }

  def handleSave(): Unit = {
    CommandRegistry.executeCommand(new MakeSnapshotCommand)
    println("SNAPSHOT SAVED")
  }

  def handleRestore(): Unit = {
    try {
      CommandRegistry.executeCommand(new RestoreSnapshotCommand)
      println("LAST SNAPSHOT RESTORED")
    } catch {
      case e: EmptyHistoryException => println("HISTORY IS EMPTY")
    }
  }

  def handleActivate(tail: Array[String]): Unit = {
    if (tail.length != 1) {
      println("INCORRECT NUMBER OF ARGS")
    } else {
      try {
        currentCar = Some(operationHandler.findCar(tail.head.toInt).getOrElse(throw new CarNotFoundException(tail.head.toInt)).car)
        println("CAR ACTIVATED")
      } catch {
        case e: CarNotFoundException => println("CAR DOES NOT EXIST")
      }
    }
  }


  def handleSudo(args: Array[String]): Unit = {
    print("login: ")
    val login = StdIn.readLine()
    print("password: ")
    val password = StdIn.readLine()
    if (login != "admin" && password != "admin") {
      println("WRONG LOGIN OR PASSWORD")
    } else {
      operationHandler.setState(Admin)
      handleCommand(args)
    }
  }

  def handleLogout(): Unit = {
    operationHandler.setState(Moderator)
  }

  var currentCar: Option[Car] = None

  def main(args: Array[String]): Unit = {
    while (true) {
      val command = StdIn.readLine().split(" ")
      handleCommand(command)
    }
  }

  def handleSendMessage(tail: Array[String]): Unit = {
    currentCar match {
      case Some(car) =>
        if (tail.length != 2) {
          println("INCORRECT NUMBER OF ARGS")
        } else {
          try {
            car.sendMessage(tail(0).toInt, tail.tail.mkString(" "))
            println("MESSAGE SENT")
          } catch {
            case e: ForbiddenMessage => println("FORBIDDEN MESSAGE")
          }
        }
      case None => println("YOU HAVE TO ACTIVATE CAR FIRST")
    }
  }

  def handleReadMessage(): Unit = {
    currentCar match {
      case Some(car) => car.readMessages()
      case None => println("YOU HAVE TO ACTIVATE CAR FIRST")
    }
  }

  private def handleCommand(command: Array[String]): Unit = {
    command.head match {
      case Commands.CREATE => handleCreate(command.tail)
      case Commands.DELETE => handleDelete(command.tail)
      case Commands.PRINT => handlePrint(command.tail)
      case Commands.IMPROVE => handleImprove(command.tail)
      case Commands.COSTS => handleCosts()
      case Commands.SAVE => handleSave()
      case Commands.RESTORE => handleRestore()
      case Commands.ACTIVATE => handleActivate(command.tail)
      case Commands.SEND_MESSAGE => handleSendMessage(command.tail)
      case Commands.READ_MESSAGE => handleReadMessage()
      case Commands.SUDO => handleSudo(command.tail)
      case Commands.LOGOUT => handleLogout()
      case Commands.UNDO => handleUndo()
      case Commands.REDO => handleRedo()
      case Commands.HELP => handleHelp()
      case Commands.EXIT => handleExit()
      case c => println("UNKNOWN COMMAND " + c)
    }
  }
}
