package DesignPatterns.model

import DesignPatterns.model.EngineType.EngineType
import DesignPatterns.model.QualityType.QualityType

class Kombi(engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val bootCapacity: Int)
  extends Car(engineType, qualityType, maxSpeed, 5) {
  override def toString: String = {
    super.toString + ", " + bootCapacity + "l boot capacity"
  }

  override def cloneCar(): Car = {
    new Kombi(engineType, qualityType, maxSpeed, bootCapacity)
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case other: Kombi =>
        super.equals(other) && this.bootCapacity == other.bootCapacity
      case _ =>
        false
    }
  }
}
