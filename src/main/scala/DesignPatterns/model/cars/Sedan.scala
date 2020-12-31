package DesignPatterns.model.cars

import DesignPatterns.model.cars.EngineType.EngineType
import DesignPatterns.model.cars.QualityType.QualityType
import DesignPatterns.singleton.CarProducer

class Sedan(engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val doorsNumber: Int, skipAdding: Boolean = false)
  extends Car(engineType, qualityType, maxSpeed, 5) {

  if (!skipAdding) {
    CarProducer.getInstance().addCar(this)
  }
  override def toString: String = {
    super.toString + ", " + doorsNumber + " doors"
  }

  override def cloneCar(): Car = {
    val clone = new Sedan(engineType, qualityType, maxSpeed, doorsNumber, skipAdding = true)
    clone.setSerialNumber(serialNumber)
    clone
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case other: Sedan =>
        super.equals(other) && this.doorsNumber == other.doorsNumber
      case _ =>
        false
    }
  }
}
