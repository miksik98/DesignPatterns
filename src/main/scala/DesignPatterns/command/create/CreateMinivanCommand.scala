package DesignPatterns.command.create

import DesignPatterns.facade.OperationHandler

class CreateMinivanCommand(implicit operationHandler: OperationHandler) extends CreateCarCommand(operationHandler)  {
  override def execute(): Unit = {
    car = Some(operationHandler.createMinivan())
  }
  override def getName: String = "Creating minivan"
}
