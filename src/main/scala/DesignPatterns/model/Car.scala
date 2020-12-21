package DesignPatterns.model

import DesignPatterns.model.EngineType.EngineType
import DesignPatterns.model.QualityType.QualityType
import DesignPatterns.prototype.CarPrototype

object CarType extends Enumeration {
  type CarType = Value
  val Sedan, Minivan, Kombi, Kabriolet = Value
}

object EngineType extends Enumeration {
  type EngineType = Value
  val Diesel, LPG = Value
}

object QualityType extends Enumeration {
  type QualityType = Value
  val Low, Medium, High = Value
}

abstract class Car(var engineType: EngineType, val qualityType: QualityType, var  maxSpeed: Int, val seatsNumber: Int)
  extends CarPrototype {

  val carType: String = this.getClass.getSimpleName

  def changeEngine(): Unit = {
    engineType = EngineType.values.filter(!_.equals(engineType)).head
  }

  def engineTuning(): Unit = {
    maxSpeed = maxSpeed + 10
  }

  override def toString: String = {
    carType + " - " + engineType + ", " + qualityType + " quality, " + maxSpeed + " max speed, " + seatsNumber + " seats"
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case other: Car =>
        this.engineType == other.engineType && this.qualityType == other.qualityType &&
          this.maxSpeed == other.maxSpeed && this.seatsNumber == other.seatsNumber
      case _ =>
        false
    }
  }
}
