package DesignPatterns.model

import DesignPatterns.BasicTest
import DesignPatterns.model.cars.{EngineType, QualityType, Sedan}

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
    car.maxSeatsNumber == 5
  }

  test("remove seats test") {
    car.takeAwaySeats(5) shouldBe false
    car.takeAwaySeats(3) shouldBe true
    car.actualSeatsNumber shouldBe 2
  }

  test("put new seats test") {
    car.putSeats(7) shouldBe false
    car.putSeats(2) shouldBe true
    car.actualSeatsNumber shouldBe 4
    car.putSeats(1) shouldBe true
    car.actualSeatsNumber shouldBe 5
  }
}
