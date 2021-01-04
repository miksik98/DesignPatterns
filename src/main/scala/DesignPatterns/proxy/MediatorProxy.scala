package DesignPatterns.proxy

import DesignPatterns.mediator.CommunicationMediator
import DesignPatterns.singleton.CarProducer

object MediatorProxy extends CommunicationMediator {
  private val mediator: CommunicationMediator = CarProducer.getInstance()

  override def forwardMessage(serialNumber: Int, message: String): Unit = {
    if (!message.contains("admin")) {
      mediator.forwardMessage(serialNumber, message)
    } else {
      throw new ForbiddenMessage
    }
  }
}

class ForbiddenMessage extends RuntimeException
