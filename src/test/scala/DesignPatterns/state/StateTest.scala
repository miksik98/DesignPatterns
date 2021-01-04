package DesignPatterns.state

import DesignPatterns.BasicTest
import DesignPatterns.factory.KombiFactory
import DesignPatterns.singleton.{CarProducer, InsufficientPrivileges}

class StateTest extends BasicTest {
  test("moderator test") {
    CarProducer.getInstance().setState(Moderator)
    val kombi = KombiFactory.create()
    assertThrows[InsufficientPrivileges](CarProducer.getInstance().deleteCar(kombi.serialNumber))
  }

  test("admin test") {
    CarProducer.getInstance().setState(Admin)
    val kombi = KombiFactory.create()
    val previousNumber = CarProducer.getInstance().carsNumber
    CarProducer.getInstance().deleteCar(kombi.serialNumber)
    CarProducer.getInstance().carsNumber shouldBe previousNumber - 1
    CarProducer.getInstance().getCars.contains(kombi) shouldBe false
  }
}
