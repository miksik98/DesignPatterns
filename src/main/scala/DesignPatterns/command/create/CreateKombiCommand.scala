package DesignPatterns.command.create

import DesignPatterns.facade.OperationHandler

class CreateKombiCommand(implicit operationHandler: OperationHandler) extends CreateCarCommand(operationHandler)  {
  override def execute(): Unit = {
    car = Some(operationHandler.createKombi())
  }
  override def getName: String = "Creating kombi"
}