package DesignPatterns.factory
import DesignPatterns.model.cars.{Car, Sedan}
import Generators._

object SedanFactory extends CarFactory {
  override def create(): Car = {
    new Sedan(
      EngineGenerator.generate(),
      QualityGenerator.generate(),
      150 + ExtraSpeedGenerator.generate(),
      DoorsNumberGenerator.generate()
    )
  }
}
