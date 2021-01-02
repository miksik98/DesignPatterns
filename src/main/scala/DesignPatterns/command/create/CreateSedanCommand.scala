package DesignPatterns.command.create

import DesignPatterns.facade.CreateOperationHandler

class CreateSedanCommand(implicit operationHandler: CreateOperationHandler) extends CreateCarCommand(operationHandler)  {
  override def execute(): Unit = {
    car = Some(operationHandler.createSedan())
  }
  override def getName: String = "Creating sedan"
}
