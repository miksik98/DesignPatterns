package DesignPatterns.command.delete

import DesignPatterns.command.Command
import DesignPatterns.composite.FinalProduct
import DesignPatterns.facade.OperationHandler
import DesignPatterns.singleton.CarNotFoundException

class DeleteCarCommand(serialNumber: Int)(implicit operationHandler: OperationHandler) extends Command {
  val finalProduct: FinalProduct = operationHandler.findCar(serialNumber).getOrElse(throw new CarNotFoundException(serialNumber))

  override def execute(): Unit = {
    operationHandler.deleteCar(serialNumber)
  }

  override def undo(): Unit = {
    operationHandler.createCarWithSubContractor(
      finalProduct.subContractor.getOrElse(sys.error("parent of final product not set")), finalProduct.car)
  }

  override def redo(): Unit = execute()

  override def getName: String = "Deleting car with serialnumber " + serialNumber
}
