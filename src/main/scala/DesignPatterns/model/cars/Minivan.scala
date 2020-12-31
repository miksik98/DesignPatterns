package DesignPatterns.model.cars

import DesignPatterns.model.cars.EngineType.EngineType
import DesignPatterns.model.cars.QualityType.QualityType
import DesignPatterns.singleton.CarProducer

class Minivan (engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val weight: Int, skipAdding: Boolean = false)
  extends Car(engineType, qualityType, maxSpeed, 8) {

  if (!skipAdding) {
    CarProducer.getInstance().addCar(this)
  }
  override def toString: String = {
    super.toString + ", " + weight + " weight"
  }

  override def cloneCar(): Car = {
    val clone = new Minivan(engineType, qualityType, maxSpeed, weight, skipAdding = true)
    clone.setSerialNumber(serialNumber)
    clone
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case other: Minivan =>
        super.equals(other) && this.weight == other.weight
      case _ =>
        false
    }
  }

  override def getQualityType: QualityType = {
    qualityType
  }
}
