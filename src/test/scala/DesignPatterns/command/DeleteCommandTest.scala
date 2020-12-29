package DesignPatterns.command

import DesignPatterns.BasicTest
import DesignPatterns.command.delete.DeleteCarCommand
import DesignPatterns.composite.SubContractorGenerator
import DesignPatterns.facade.BasicOperationHandler
import DesignPatterns.factory.Generators.CarGenerator
import DesignPatterns.model.cars.Car
import DesignPatterns.singleton.CarProducer

class DeleteCommandTest extends BasicTest {
  private implicit val operationHandler: BasicOperationHandler = new BasicOperationHandler

  test("delete command test") {
    Range(0,10).foreach(i => CarGenerator.generate())
    val lastCar: Car = CarProducer.getInstance().getCars.last
    val command = new DeleteCarCommand(lastCar.serialNumber)
    val prevCarsNumber = CarProducer.getInstance().carsNumber
    CommandRegistry.executeCommand(command)
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber + 1
    CarProducer.getInstance().getCars.contains(lastCar) shouldBe false
    CarProducer.getInstance().getCars.exists(car => lastCar.serialNumber == car.serialNumber) shouldBe false
    CommandRegistry.undo()
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber
    CarProducer.getInstance().getCars.contains(lastCar) shouldBe true
    CarProducer.getInstance().getCars.exists(car => lastCar.serialNumber == car.serialNumber) shouldBe true
    CommandRegistry.redo()
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber + 1
    CarProducer.getInstance().getCars.contains(lastCar) shouldBe false
    CarProducer.getInstance().getCars.exists(car => lastCar.serialNumber == car.serialNumber) shouldBe false
    command.getName shouldBe "Deleting car with serialnumber " + lastCar.serialNumber
  }
}
