package DesignPatterns.command.create

import DesignPatterns.command.Command
import DesignPatterns.facade.CreateOperationHandler
import DesignPatterns.model.cars.Car

abstract class CreateCarCommand(operationHandler: CreateOperationHandler) extends Command {
  protected var car: Option[Car] = None

  override def undo(): Unit = {
    if (car.isEmpty) {
      sys.error("Undoing not executed command")
    }
    operationHandler.deleteCar(car.get.serialNumber)
  }

  override def redo(): Unit = {
    if (car.isEmpty) {
      sys.error("Redoing not executed command")
    }
    operationHandler.createCar(car.get)
  }
}
