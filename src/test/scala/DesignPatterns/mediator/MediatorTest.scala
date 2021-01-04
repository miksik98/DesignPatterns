package DesignPatterns.mediator

import DesignPatterns.BasicTest
import DesignPatterns.factory.{KombiFactory, SedanFactory}

class MediatorTest extends BasicTest {
  private val msg0 = "HELLO"

  test("communication test") {
    val kombi = KombiFactory.create()
    val sedan = SedanFactory.create()
    kombi.sendMessage(sedan.serialNumber, msg0)
    sedan.getMessages shouldBe Array(kombi.prepareMessage(msg0))
    sedan.readMessages()
    sedan.getMessages.length shouldBe 0
  }
}
