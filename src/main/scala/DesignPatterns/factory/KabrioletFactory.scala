package DesignPatterns.factory
import DesignPatterns.model.{Car, Kabriolet}
import Generators._

object KabrioletFactory extends CarFactory {
  override def create(): Car = {
    new Kabriolet(
      EngineGenerator.generate(),
      QualityGenerator.generate(),
      100 + ExtraSpeedGenerator.generate(),
      RoofExistenceGenerator.generate()
    )
  }
}
