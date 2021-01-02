package DesignPatterns.command.create

import DesignPatterns.facade.CreateOperationHandler

class CreateKabrioletCommand(implicit operationHandler: CreateOperationHandler) extends CreateCarCommand(operationHandler) {
  override def execute(): Unit = {
    car = Some(operationHandler.createKabriolet())
  }
  override def getName: String = "Creating kabriolet"
}
