package DesignPatterns.facade

import DesignPatterns.BasicTest
import DesignPatterns.model.cars.{Kabriolet, Kombi, Minivan, Sedan}
import DesignPatterns.singleton.CarProducer

class FacadeTest extends BasicTest {
  private val operationHandler = new BasicOperationHandler
  test("create sedan") {
    operationHandler.createSedan()
    CarProducer.getInstance().carsNumber shouldBe 1
    CarProducer.getInstance().getCars.exists(c => c.carType == classOf[Sedan].getSimpleName) shouldBe true
  }

  test("create minivan") {
    operationHandler.createMinivan()
    CarProducer.getInstance().carsNumber shouldBe 2
    CarProducer.getInstance().getCars.exists(c => c.carType == classOf[Minivan].getSimpleName) shouldBe true
  }

  test("create kombi") {
    operationHandler.createKombi()
    CarProducer.getInstance().carsNumber shouldBe 3
    CarProducer.getInstance().getCars.exists(c => c.carType == classOf[Kombi].getSimpleName) shouldBe true
  }

  test("create kabriolet") {
    operationHandler.createKabriolet()
    CarProducer.getInstance().carsNumber shouldBe 4
    CarProducer.getInstance().getCars.exists(c => c.carType == classOf[Kabriolet].getSimpleName) shouldBe true
  }

  test("delete car") {
    operationHandler.createSedan()
    val prevCarsNumber = CarProducer.getInstance().carsNumber
    val serialNumber = CarProducer.getInstance().getCars.last.serialNumber
    operationHandler.deleteCar(serialNumber)
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber + 1
    CarProducer.getInstance().getCars.exists(c => c.serialNumber == serialNumber) shouldBe false
  }
}
