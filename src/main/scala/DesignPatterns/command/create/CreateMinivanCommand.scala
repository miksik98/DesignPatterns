package DesignPatterns.command.create

import DesignPatterns.facade.CreateOperationHandler

class CreateMinivanCommand(implicit operationHandler: CreateOperationHandler) extends CreateCarCommand(operationHandler)  {
  override def execute(): Unit = {
    car = Some(operationHandler.createMinivan())
  }
  override def getName: String = "Creating minivan"
}
