package DesignPatterns.command.create

import DesignPatterns.facade.OperationHandler

class CreateKabrioletCommand(implicit operationHandler: OperationHandler) extends CreateCarCommand(operationHandler) {
  override def execute(): Unit = {
    car = Some(operationHandler.createKabriolet())
  }
  override def getName: String = "Creating kabriolet"
}
