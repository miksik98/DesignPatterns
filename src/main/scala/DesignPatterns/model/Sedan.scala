package DesignPatterns.model
import DesignPatterns.model.EngineType.EngineType
import DesignPatterns.model.QualityType.QualityType

class Sedan(engineType: EngineType, qualityType: QualityType, maxSpeed: Int, val doorsNumber: Int)
  extends Car(engineType, qualityType, maxSpeed, 5) {
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
