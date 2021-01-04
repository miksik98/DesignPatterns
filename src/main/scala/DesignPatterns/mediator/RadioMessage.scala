package DesignPatterns.mediator

import DesignPatterns.singleton.CarNotFoundException

trait RadioMessage {
  private var messages: Seq[String] = Seq.empty
  protected val mediator: CommunicationMediator

  def prepareMessage(message: String): String

  def sendMessage(serialNumber: Int, message: String): Unit = {
    try {
      mediator.forwardMessage(serialNumber, prepareMessage(message))
    } catch {
      case e: CarNotFoundException => println("Car with " + serialNumber + " does not exists.")
    }
  }

  def addMessage(message: String): Unit = {
    messages = messages :+ message
  }

  def getMessages: Seq[String] = messages

  def readMessages(): Unit = {
    if (messages.isEmpty) {
      println("no new messages")
    } else {
      println(messages.mkString("\n"))
      messages = Seq.empty
    }
  }

}
