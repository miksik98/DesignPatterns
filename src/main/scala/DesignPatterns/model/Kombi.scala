package DesignPatterns.model

import DesignPatterns.model.EngineType.EngineType
import DesignPatterns.model.QualityType.QualityType

class Kombi(engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val bootCapacity: Int)
  extends Car(engineType, qualityType, maxSpeed, 5) {
  override def toString: String = {
    super.toString + ", " + bootCapacity + "l boot capacity"
  }
}
