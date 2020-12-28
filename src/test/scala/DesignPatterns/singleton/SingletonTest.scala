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
    val prevCars = CarProducer.getInstance().getCars
    CarProducer.getInstance().deleteCar(kabriolet.serialNumber)
    prevCars.length shouldBe CarProducer.getInstance().getCars.length + 1
  }

  test("deleting big tree test") {
    CarProducer.getInstance().addComponent(SubContractorGenerator.generate())
    CarProducer.getInstance().addComponent(SubContractorGenerator.generate())
    CarProducer.getInstance().addComponent(SubContractorGenerator.generate())
    val car = CarProducer.getInstance().components.last.getCars.last
    val prevCars = CarProducer.getInstance().getCars
    CarProducer.getInstance().deleteCar(car.serialNumber)
    prevCars.length shouldBe CarProducer.getInstance().getCars.length + 1
  }
}
