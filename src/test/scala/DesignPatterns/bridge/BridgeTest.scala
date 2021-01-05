package DesignPatterns.bridge

import DesignPatterns.bridge.finders.{FaultyCarsFinder, KombiFinder, SpeedCarsFinder}
import DesignPatterns.bridge.services.CarSearchService
import DesignPatterns.model.cars.{EngineType, Kabriolet, Kombi, Minivan, QualityType, Sedan}
import DesignPatterns.singleton.CarProducer
import org.scalatest.{BeforeAndAfterAll, FunSuite, Matchers}

class BridgeTest extends FunSuite with Matchers with BeforeAndAfterAll {
  override def beforeAll(): Unit = {
    CarProducer.getInstance().reset()
    val car0 = new Kombi(EngineType.LPG, QualityType.Low, 200, 50)
    val car1 = new Sedan(EngineType.Diesel, QualityType.Medium, 190, 2)
    val car2 = new Kombi(EngineType.Diesel, QualityType.High, 189, 50)
    val car3 = new Minivan(EngineType.LPG, QualityType.Low, 201, 500)
  }

  test("special car search service") {
    val searchService = new CarSearchService(new KombiFinder)
    searchService.getAll.exists(!_.isInstanceOf[Kombi]) shouldBe false
    searchService.getAll.length shouldBe 2
    searchService.find(searchService.getAll.head.serialNumber).isDefined shouldBe true
  }

  test("faulty cars search service") {
    val searchService = new CarSearchService(new FaultyCarsFinder)
    searchService.getAll.exists(_.getQualityType != QualityType.Low) shouldBe false
    searchService.getAll.length shouldBe 2
  }

  test("filter cars by speed search service") {
    val lowerBound = 190
    val higherBound = 200
    val searchService = new CarSearchService(new SpeedCarsFinder(lowerBound, higherBound))
    searchService.getAll.exists(car => car.getMaxSpeed < lowerBound || car.getMaxSpeed > higherBound) shouldBe false
    searchService.getAll.length shouldBe 2
  }
}
