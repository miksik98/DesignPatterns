package DesignPatterns.model.cars

import DesignPatterns.model.cars.EngineType.EngineType
import DesignPatterns.model.cars.QualityType.QualityType
import DesignPatterns.singleton.CarProducer

class Sedan(engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val doorsNumber: Int)
  extends Car(engineType, qualityType, maxSpeed, 5) {

  CarProducer.getInstance().addCar(this)

  override def toString: String = {
    super.toString + ", " + doorsNumber + " doors"
  }

  override def cloneCar(): Car = {
    new Sedan(engineType, qualityType, maxSpeed, doorsNumber)
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
