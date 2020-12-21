package DesignPatterns.model
import DesignPatterns.model.EngineType.EngineType
import DesignPatterns.model.QualityType.QualityType

class Sedan(engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val doorsNumber: Int)
  extends Car(engineType, qualityType, maxSpeed, 5) {
  override def toString: String = {
    super.toString + ", " + doorsNumber + " doors"
  }
}