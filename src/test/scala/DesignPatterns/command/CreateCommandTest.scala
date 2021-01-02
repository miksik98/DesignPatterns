package DesignPatterns.command

import DesignPatterns.BasicTest
import DesignPatterns.command.create.{CreateKabrioletCommand, CreateKombiCommand, CreateMinivanCommand, CreateSedanCommand}
import DesignPatterns.facade.{BasicOperationHandler, CreateOperationHandler}
import DesignPatterns.model.cars.{Kabriolet, Kombi, Minivan, Sedan}
import DesignPatterns.singleton.CarProducer

class CreateCommandTest extends BasicTest {
  private implicit val operationHandler: CreateOperationHandler = new BasicOperationHandler

  test("sedan command test") {
    val command = new CreateSedanCommand
    val prevCarsNumber = CarProducer.getInstance().carsNumber
    CommandRegistry.executeCommand(command)
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber - 1
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Sedan]) shouldBe true
    val sedan = CarProducer.getInstance().getCars.find(car => car.isInstanceOf[Sedan]).get
    CommandRegistry.undo()
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Sedan]) shouldBe false
    CarProducer.getInstance().getCars.exists(car => car.serialNumber == sedan.serialNumber) shouldBe false
    CarProducer.getInstance().getCars.contains(sedan) shouldBe false
    CommandRegistry.redo()
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber - 1
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Sedan]) shouldBe true
    CarProducer.getInstance().getCars.exists(car => car.serialNumber == sedan.serialNumber) shouldBe true
    CarProducer.getInstance().getCars.contains(sedan) shouldBe true
    command.getName shouldBe "Creating sedan"
  }

  test("minivan command test") {
    val command = new CreateMinivanCommand
    val prevCarsNumber = CarProducer.getInstance().carsNumber
    CommandRegistry.executeCommand(command)
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber - 1
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Minivan]) shouldBe true
    val minivan = CarProducer.getInstance().getCars.find(car => car.isInstanceOf[Minivan]).get
    CommandRegistry.undo()
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Minivan]) shouldBe false
    CarProducer.getInstance().getCars.exists(car => car.serialNumber == minivan.serialNumber) shouldBe false
    CarProducer.getInstance().getCars.contains(minivan) shouldBe false
    CommandRegistry.redo()
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber - 1
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Minivan]) shouldBe true
    CarProducer.getInstance().getCars.exists(car => car.serialNumber == minivan.serialNumber) shouldBe true
    CarProducer.getInstance().getCars.contains(minivan) shouldBe true
    command.getName shouldBe "Creating minivan"
  }

  test("kombi command test") {
    val command = new CreateKombiCommand
    val prevCarsNumber = CarProducer.getInstance().carsNumber
    CommandRegistry.executeCommand(command)
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber - 1
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Kombi]) shouldBe true
    val kombi = CarProducer.getInstance().getCars.find(car => car.isInstanceOf[Kombi]).get
    CommandRegistry.undo()
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Kombi]) shouldBe false
    CarProducer.getInstance().getCars.exists(car => car.serialNumber == kombi.serialNumber) shouldBe false
    CarProducer.getInstance().getCars.contains(kombi) shouldBe false
    CommandRegistry.redo()
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber - 1
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Kombi]) shouldBe true
    CarProducer.getInstance().getCars.exists(car => car.serialNumber == kombi.serialNumber) shouldBe true
    CarProducer.getInstance().getCars.contains(kombi) shouldBe true
    command.getName shouldBe "Creating kombi"
  }

  test("kabriolet command test") {
    val command = new CreateKabrioletCommand
    val prevCarsNumber = CarProducer.getInstance().carsNumber
    CommandRegistry.executeCommand(command)
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber - 1
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Kabriolet]) shouldBe true
    val kabriolet = CarProducer.getInstance().getCars.find(car => car.isInstanceOf[Kabriolet]).get
    CommandRegistry.undo()
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Kabriolet]) shouldBe false
    CarProducer.getInstance().getCars.exists(car => car.serialNumber == kabriolet.serialNumber) shouldBe false
    CarProducer.getInstance().getCars.contains(kabriolet) shouldBe false
    CommandRegistry.redo()
    prevCarsNumber shouldBe CarProducer.getInstance().carsNumber - 1
    CarProducer.getInstance().getCars.exists(car => car.isInstanceOf[Sedan]) shouldBe true
    CarProducer.getInstance().getCars.exists(car => car.serialNumber == kabriolet.serialNumber) shouldBe true
    CarProducer.getInstance().getCars.contains(kabriolet) shouldBe true
    command.getName shouldBe "Creating kabriolet"
  }
}
