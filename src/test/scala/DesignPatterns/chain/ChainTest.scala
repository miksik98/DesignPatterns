package DesignPatterns.chain

import DesignPatterns.bridge.finders.{FaultyCarsFinder, KombiFinder, SpeedCarsFinder}
import DesignPatterns.model.cars.{EngineType, Kombi, QualityType, Sedan}
import DesignPatterns.singleton.CarProducer
import org.scalatest.{BeforeAndAfterAll, FunSuite, Matchers}

class ChainTest extends FunSuite with Matchers {
  CarProducer.getInstance().reset()
  private val chainSearchService = new ChainSearchService(new FaultyCarsFinder)
  chainSearchService.appendFinder(new KombiFinder)
  chainSearchService.appendFinder(new SpeedCarsFinder(190, 200))
  private val car0 = new Kombi(EngineType.LPG, QualityType.Low, 200, 50)
  private val car1 = new Sedan(EngineType.Diesel, QualityType.Low, 190, 2)
  private val car2 = new Kombi(EngineType.Diesel, QualityType.High, 191, 50)
  private val car3 = new Kombi(EngineType.LPG, QualityType.Low, 201, 50)

  test("chain with kombi/speed filter test") {
    val map = new FiltersMap
    map.setFilter(FilterName.Kombi)
    map.setFilter(FilterName.Speed)
    chainSearchService.getCars(new FilterRequest(map)) shouldBe Seq(car0, car2)
  }

  test("chain with kombi/faulty filter test") {
    val map = new FiltersMap
    map.setFilter(FilterName.Kombi)
    map.setFilter(FilterName.Faulty)
    chainSearchService.getCars(new FilterRequest(map)) shouldBe Seq(car0, car3)
  }

  test("chain with speed/faulty filter test") {
    val map = new FiltersMap
    map.setFilter(FilterName.Speed)
    map.setFilter(FilterName.Faulty)
    chainSearchService.getCars(new FilterRequest(map)) shouldBe Seq(car0, car1)
  }

  test("chain with all filters test") {
    val map = new FiltersMap
    map.setFilter(FilterName.Speed)
    map.setFilter(FilterName.Faulty)
    map.setFilter(FilterName.Kombi)
    chainSearchService.getCars(new FilterRequest(map)) shouldBe Seq(car0)
  }

  test("chain with kombi filter test") {
    val map = new FiltersMap
    map.setFilter(FilterName.Kombi)
    chainSearchService.getCars(new FilterRequest(map)) shouldBe Seq(car0, car2, car3)
  }

  test("chain with faulty filter test") {
    val map = new FiltersMap
    map.setFilter(FilterName.Faulty)
    chainSearchService.getCars(new FilterRequest(map)) shouldBe Seq(car0, car1, car3)
  }

  test("chain with speed filter test") {
    val map = new FiltersMap
    map.setFilter(FilterName.Speed)
    chainSearchService.getCars(new FilterRequest(map)) shouldBe Seq(car0, car1, car2)
  }

  test("chain with no filters test") {
    val map = new FiltersMap
    chainSearchService.getCars(new FilterRequest(map)) shouldBe Seq(car0, car1, car2, car3)
  }
}
