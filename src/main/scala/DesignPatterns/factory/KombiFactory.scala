package DesignPatterns.factory
import DesignPatterns.model.{Car, Kombi}

class KombiFactory extends CarFactory {
  override def create(): Car = {
    new Kombi(EngineGenerator.generate(), QualityGenerator.generate(), 140 + ExtraSpeedGenerator.generate())
  }
}
