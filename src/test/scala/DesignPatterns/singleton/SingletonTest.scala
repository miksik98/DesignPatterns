package DesignPatterns.singleton

import DesignPatterns.BasicTest
import DesignPatterns.factory.{KabrioletFactory, KombiFactory, MinivanFactory, SedanFactory}

class SingletonTest extends BasicTest {
  test("car producer test") {
    val startingNumber = CarProducer.getInstance().carsNumber
    KabrioletFactory.create()
    KombiFactory.create()
    MinivanFactory.create()
    SedanFactory.create()
    CarProducer.getInstance().carsNumber - startingNumber shouldBe 4
  }
}
