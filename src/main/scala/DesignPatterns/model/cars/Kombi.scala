package DesignPatterns.model.cars

import DesignPatterns.model.TrunkCompatibility
import DesignPatterns.model.cars.EngineType.EngineType
import DesignPatterns.model.cars.QualityType.QualityType
import DesignPatterns.singleton.CarProducer

class Kombi(engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val trunkCapacity: Int)
  extends Car(engineType, qualityType, maxSpeed, 5) with TrunkCompatibility {

  CarProducer.getInstance().addCar(this)

  override def toString: String = {
    super.toString + ", " + trunkCapacity + "l boot capacity"
  }

  override def cloneCar(): Car = {
    new Kombi(engineType, qualityType, maxSpeed, trunkCapacity)
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
