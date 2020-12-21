package DesignPatterns.model

import org.scalatest.{FunSuite, Matchers}

class CarTest extends FunSuite with Matchers {
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
