package DesignPatterns.command.create

import DesignPatterns.facade.OperationHandler

class CreateSedanCommand(implicit operationHandler: OperationHandler) extends CreateCarCommand(operationHandler)  {
  override def execute(): Unit = {
    car = Some(operationHandler.createSedan())
  }
  override def getName: String = "Creating sedan"
}
