package DesignPatterns.command

import DesignPatterns.command.improve.ImproveFaultyCarsCommand
import DesignPatterns.facade.{BasicOperationHandler, ImproveOperationHandler}
import DesignPatterns.model.cars.{Car, EngineType, Kombi, QualityType}
import DesignPatterns.singleton.CarProducer
import org.scalatest.{FunSuite, Matchers}

class ImproveFaultyCarsCommandTest extends FunSuite with Matchers {
  private implicit val operationHandler: ImproveOperationHandler = new BasicOperationHandler
  CarProducer.getInstance().reset()
  private val kombiLow = new Kombi(EngineType.LPG, QualityType.Low, 120, 60)
  private val kombiMedium = new Kombi(EngineType.LPG, QualityType.Medium, 120, 60)

  test("should register all cars") {
    CarProducer.getInstance().carsNumber shouldBe 2
    CarProducer.getInstance().getCars.contains(kombiLow) shouldBe true
    CarProducer.getInstance().getCars.contains(kombiMedium) shouldBe true
  }

  test("should improve quality types") {
    CommandRegistry.executeCommand(new ImproveFaultyCarsCommand)
    kombiLow.getQualityType shouldBe QualityType.Medium
    kombiMedium.getQualityType shouldBe QualityType.High
  }

  test("should undo operation") {
    CommandRegistry.undo()
    kombiLow.getQualityType shouldBe QualityType.Low
    kombiMedium.getQualityType shouldBe QualityType.Medium
  }

  test("should redo operation") {
    CommandRegistry.redo()
    kombiLow.getQualityType shouldBe QualityType.Medium
    kombiMedium.getQualityType shouldBe QualityType.High
  }

  test("should improve quality types, after renew subscription") {
    CommandRegistry.undo()
    CommandRegistry.executeCommand(new ImproveFaultyCarsCommand)
    kombiLow.getQualityType shouldBe QualityType.Medium
    kombiMedium.getQualityType shouldBe QualityType.High
  }

  test("command name test") {
    new ImproveFaultyCarsCommand().getName shouldBe "Improving quality of faulty cars"
  }
}
