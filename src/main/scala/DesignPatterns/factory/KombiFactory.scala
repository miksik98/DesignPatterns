package DesignPatterns.factory
import DesignPatterns.model.cars.{Car, Kombi}
import Generators._

object KombiFactory extends CarFactory {
  override def create(): Car = {
    new Kombi(
      EngineGenerator.generate(),
      QualityGenerator.generate(),
      140 + ExtraSpeedGenerator.generate(),
      BootCapacityGenerator.generate()
    )
  }
}
