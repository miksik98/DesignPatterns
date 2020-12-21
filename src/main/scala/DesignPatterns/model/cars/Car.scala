package DesignPatterns.model.cars

import DesignPatterns.model.cars.EngineType.EngineType
import DesignPatterns.model.cars.QualityType.QualityType
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

abstract class Car(var engineType: EngineType, val qualityType: QualityType, var  maxSpeed: Int, val maxSeatsNumber: Int)
  extends CarPrototype {

  val carType: String = this.getClass.getSimpleName
  var actualSeatsNumber: Int = maxSeatsNumber

  def changeEngine(): Unit = {
    engineType = EngineType.values.filter(!_.equals(engineType)).head
  }

  def takeAwaySeats(seatsNumber: Int): Boolean = {
    if (actualSeatsNumber - seatsNumber > 0) {
      actualSeatsNumber = actualSeatsNumber - seatsNumber
      true
    } else {
      false
    }
  }

  def putSeats(seatsNumber: Int): Boolean = {
    if (actualSeatsNumber + seatsNumber <= maxSeatsNumber) {
      actualSeatsNumber = actualSeatsNumber + seatsNumber
      true
    } else {
      false
    }
  }

  def getCapacityFromRemovedSeats: Int = {
    (maxSeatsNumber - actualSeatsNumber) * 100
  }

  def engineTuning(): Unit = {
    maxSpeed = maxSpeed + 10
  }

  override def toString: String = {
    carType + " - " + engineType + ", " + qualityType + " quality, " + maxSpeed + " max speed, " + maxSeatsNumber + " seats"
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case other: Car =>
        this.engineType == other.engineType && this.qualityType == other.qualityType &&
          this.maxSpeed == other.maxSpeed && this.maxSeatsNumber == other.maxSeatsNumber
      case _ =>
        false
    }
  }
}
