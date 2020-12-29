package DesignPatterns.singleton

import DesignPatterns.BasicTest
import DesignPatterns.composite.SubContractorGenerator
import DesignPatterns.factory.{KabrioletFactory, KombiFactory, MinivanFactory, SedanFactory}

class SingletonTest extends BasicTest {
  test("should throw exception, when deleting non existing car") {
    assertThrows[CarNotFoundException](CarProducer.getInstance().deleteCar(1000))
  }

  test("car producer test") {
    val startingNumber = CarProducer.getInstance().carsNumber
    KabrioletFactory.create()
    KombiFactory.create()
    MinivanFactory.create()
    SedanFactory.create()
    CarProducer.getInstance().carsNumber - startingNumber shouldBe 4
  }

  test("deleting simple test") {
    val kabriolet = KabrioletFactory.create()
    val prevCarsNumber = CarProducer.getInstance().carsNumber
    CarProducer.getInstance().deleteCar(kabriolet.serialNumber)
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber + 1
  }

  test("deleting big tree test") {
    CarProducer.getInstance().addComponent(SubContractorGenerator.generate())
    CarProducer.getInstance().addComponent(SubContractorGenerator.generate())
    CarProducer.getInstance().addComponent(SubContractorGenerator.generate())
    val car = CarProducer.getInstance().components.last.getCars.last
    val prevCarsNumber = CarProducer.getInstance().carsNumber
    CarProducer.getInstance().deleteCar(car.serialNumber)
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber + 1
  }
}
