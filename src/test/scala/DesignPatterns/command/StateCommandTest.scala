package DesignPatterns.command

import DesignPatterns.BasicTest
import DesignPatterns.command.snapshot.{MakeSnapshotCommand, RestoreSnapshotCommand}
import DesignPatterns.facade.{BasicOperationHandler, StateOperationHandler}
import DesignPatterns.factory.KombiFactory
import DesignPatterns.singleton.CarProducer

class StateCommandTest extends BasicTest {
  private implicit val operationHandler: StateOperationHandler = new BasicOperationHandler

  test("snapshot commands test") {
    val kombi = KombiFactory.create()
    CarProducer.getInstance().carsNumber shouldBe 1
    CommandRegistry.executeCommand(new MakeSnapshotCommand)
    CarProducer.getInstance().deleteCar(kombi.serialNumber)
    CarProducer.getInstance().carsNumber shouldBe 0
    CommandRegistry.undo()
    CarProducer.getInstance().carsNumber shouldBe 0
    CommandRegistry.redo()
    CarProducer.getInstance().carsNumber shouldBe 0
    CommandRegistry.executeCommand(new RestoreSnapshotCommand)
    CarProducer.getInstance().carsNumber shouldBe 1
    CommandRegistry.undo()
    CarProducer.getInstance().carsNumber shouldBe 0
    CommandRegistry.redo()
    CarProducer.getInstance().carsNumber shouldBe 1
  }

  test("make commmand name test") {
    new MakeSnapshotCommand().getName shouldBe "Making snapshot of car producer's state"
  }

  test("restore command name test") {
    new RestoreSnapshotCommand().getName shouldBe "Restoring last saved car producer's snapshot"
  }
}
