package DesignPatterns.strategy

import DesignPatterns.BasicTest
import DesignPatterns.factory.KombiFactory
import DesignPatterns.model.cars.QualityType

class StrategyTest extends BasicTest {
  test("simple strategy test") {
    val lowCar = KombiFactory.create()
    lowCar.setQualityType(QualityType.Low)
    val mediumCar = KombiFactory.create()
    mediumCar.setQualityType(QualityType.Medium)
    SimpleImproveStrategy.execute(lowCar)
    SimpleImproveStrategy.execute(mediumCar)
    lowCar.getQualityType shouldBe QualityType.Medium
    mediumCar.getQualityType shouldBe QualityType.High
  }

  test("super strategy test") {
    val lowCar = KombiFactory.create()
    lowCar.setQualityType(QualityType.Low)
    val mediumCar = KombiFactory.create()
    mediumCar.setQualityType(QualityType.Medium)
    SuperImproveStrategy.execute(lowCar)
    SuperImproveStrategy.execute(mediumCar)
    lowCar.getQualityType shouldBe QualityType.High
    mediumCar.getQualityType shouldBe QualityType.High
  }
}
