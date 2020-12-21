package DesignPatterns.model

import DesignPatterns.model.EngineType.EngineType
import DesignPatterns.model.QualityType.QualityType
import DesignPatterns.singleton.CarProducer

class Minivan (engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val weight: Int)
  extends Car(engineType, qualityType, maxSpeed, 8) {

  CarProducer.getInstance().addCar(this)

  override def toString: String = {
    super.toString + ", " + weight + " weight"
  }

  override def cloneCar(): Car = {
    new Minivan(engineType, qualityType, maxSpeed, weight)
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case other: Minivan =>
        super.equals(other) && this.weight == other.weight
      case _ =>
        false
    }
  }
}
