package DesignPatterns.model.cars

import DesignPatterns.model.TrunkCompatibility
import DesignPatterns.model.cars.EngineType.EngineType
import DesignPatterns.model.cars.QualityType.QualityType
import DesignPatterns.singleton.CarProducer

class Kombi(engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val trunkCapacity: Int, skipAdding: Boolean = false)
  extends Car(engineType, qualityType, maxSpeed, 5) with TrunkCompatibility {

  if (!skipAdding) {
    CarProducer.getInstance().addCar(this)
  }
  override def toString: String = {
    super.toString + ", " + trunkCapacity + "l boot capacity"
  }

  override def cloneCar(): Car = {
    val clone = new Kombi(engineType, qualityType, maxSpeed, trunkCapacity, skipAdding = true)
    clone.setSerialNumber(serialNumber)
    clone
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case other: Kombi =>
        super.equals(other) && this.trunkCapacity == other.trunkCapacity
      case _ =>
        false
    }
  }

  override def getTrunkCapacity: Int = trunkCapacity + getCapacityFromRemovedSeats
}
