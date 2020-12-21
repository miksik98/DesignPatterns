package DesignPatterns.model

import DesignPatterns.BasicTest

class CarTest extends BasicTest {
  val car = new Sedan(EngineType.Diesel, QualityType.Medium, maxSpeed = 200, doorsNumber = 4)

  test("carType test") {
    car.carType shouldBe "Sedan"
  }

  test("tuning test") {
    car.engineTuning()
    car.maxSpeed shouldBe 210
  }

  test("change engine type test") {
    car.changeEngine()
    car.engineType shouldBe EngineType.LPG
  }

  test("get number of seats test") {
    car.seatsNumber == 5
  }
}
