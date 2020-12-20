package DesignPatterns.factory
import DesignPatterns.model.{Car, Minivan}

class MinivanFactory extends CarFactory {
  override def create(): Car = {
    new Minivan(EngineGenerator.generate(), QualityGenerator.generate(), 80 + ExtraSpeedGenerator.generate())
  }
}
