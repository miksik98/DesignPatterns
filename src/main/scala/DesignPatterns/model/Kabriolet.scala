package DesignPatterns.model

import DesignPatterns.model.EngineType.EngineType
import DesignPatterns.model.QualityType.QualityType

class Kabriolet (engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val hasRoof: Boolean)
  extends Car(engineType, qualityType, maxSpeed, 2) {
  override def toString: String = {
    if (!hasRoof) {
      super.toString
    } else {
      super.toString + ", with roof"
    }
  }
}
