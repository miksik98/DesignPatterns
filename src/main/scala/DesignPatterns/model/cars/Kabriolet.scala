package DesignPatterns.model.cars

import DesignPatterns.model.cars.EngineType.EngineType
import DesignPatterns.model.cars.QualityType.QualityType
import DesignPatterns.singleton.CarProducer

class Kabriolet(engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val hasRoof: Boolean, skipAdding: Boolean = false)
  extends Car(engineType, qualityType, maxSpeed, 2) {

  if (!skipAdding) {
    CarProducer.getInstance().addCar(this)
  }

  override def toString: String = {
    if (!hasRoof) {
      super.toString
    } else {
      super.toString + ", with roof"
    }
  }

  override def cloneCar(): Car = {
    val clone = new Kabriolet(engineType, qualityType, maxSpeed, hasRoof, skipAdding = false)
    clone.setSerialNumber(serialNumber)
    clone
  }

  override def getQualityType: QualityType = {
    qualityType
  }


  override def equals(obj: Any): Boolean = {
    obj match {
      case other: Kabriolet =>
        super.equals(other) && this.hasRoof == other.hasRoof
      case _ =>
        false
    }
  }
}
