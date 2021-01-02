package DesignPatterns.command.create

import DesignPatterns.facade.CreateOperationHandler

class CreateKombiCommand(implicit operationHandler: CreateOperationHandler) extends CreateCarCommand(operationHandler)  {
  override def execute(): Unit = {
    car = Some(operationHandler.createKombi())
  }
  override def getName: String = "Creating kombi"
}