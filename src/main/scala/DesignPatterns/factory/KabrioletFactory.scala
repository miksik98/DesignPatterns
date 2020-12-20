package DesignPatterns.factory
import DesignPatterns.model.{Car, Kabriolet}

class KabrioletFactory extends CarFactory {
  override def create(): Car = {
    new Kabriolet(EngineGenerator.generate(), QualityGenerator.generate(), 100 + ExtraSpeedGenerator.generate())
  }
}
