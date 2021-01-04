package DesignPatterns.mediator

trait CommunicationMediator {
  def forwardMessage(serialNumber: Int, message: String): Unit
}
