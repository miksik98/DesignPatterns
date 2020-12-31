package DesignPatterns.observer

import DesignPatterns.BasicTest
import DesignPatterns.model.cars.{EngineType, Kombi, QualityType}

class ObserverTest extends BasicTest {
  test("subscriber test") {
    val subscriber = new CarQualityImprover
    val kombiLow = new Kombi(EngineType.LPG, QualityType.Low, 120, 60, true)
    val kombiMedium = new Kombi(EngineType.LPG, QualityType.Medium, 120, 60, true)
    subscriber.addSubscriber(kombiLow)
    subscriber.addSubscriber(kombiMedium)
    subscriber.subscribers.size shouldBe 2
    subscriber.notifySubscribers()
    subscriber.subscribers.size shouldBe 1
    kombiMedium.qualityType shouldBe QualityType.High
    kombiLow.qualityType shouldBe QualityType.Medium
    subscriber.removeSubscriber(kombiLow)
    subscriber.subscribers.size shouldBe 0
    subscriber.subscribers.contains(kombiLow) shouldBe false
  }
}
