package DesignPatterns.proxy

import DesignPatterns.BasicTest
import DesignPatterns.factory.{KombiFactory, SedanFactory}

class ProxyTest extends BasicTest {
  private val adminMessage = "admin"

  test("should throw exception when sending message with admin word") {
    val kombi = KombiFactory.create()
    assertThrows[ForbiddenMessage](MediatorProxy.forwardMessage(kombi.serialNumber, adminMessage))
  }

  test("should not send admin message") {
    val sedan = SedanFactory.create()
    val kombi = KombiFactory.create()
    assertThrows[ForbiddenMessage](sedan.sendMessage(kombi.serialNumber, adminMessage))
    kombi.getMessages shouldBe Seq()
  }
}
