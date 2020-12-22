package DesignPatterns.decorator

import DesignPatterns.BasicTest
import DesignPatterns.factory.KombiFactory

class DecoratorTest extends BasicTest {
  test("extra tuning decorator test") {
    val kombi = KombiFactory.create()
    kombi.maxSpeed = 100
    val extraTuning = new ExtraTuningDecorator(kombi)
    extraTuning.engineTuning()
    extraTuning.maxSpeed shouldBe 160
  }

  test("reverse tuning decorator test") {
    val kombi = KombiFactory.create()
    kombi.maxSpeed = 100
    val extraTuning = new ReverseTuningDecorator(kombi)
    extraTuning.engineTuning()
    extraTuning.maxSpeed shouldBe 90
  }

  test("many decorators test") {
    val kombi = KombiFactory.create()
    kombi.maxSpeed = 100
    val extraTuning = new ReverseTuningDecorator(new ExtraTuningDecorator(new ExtraTuningDecorator(kombi)))
    extraTuning.engineTuning()
    extraTuning.maxSpeed shouldBe 190
  }
}
