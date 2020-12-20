package DesignPatterns.factory
import DesignPatterns.model.{Car, Sedan}

class SedanFactory extends CarFactory {
  override def create(): Car = {
    new Sedan(EngineGenerator.generate(), QualityGenerator.generate(), 150 + ExtraSpeedGenerator.generate())
  }
}
