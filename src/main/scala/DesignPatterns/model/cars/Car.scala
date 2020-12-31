package DesignPatterns.model.cars

import DesignPatterns.decorator.Tuningable
import DesignPatterns.factory.Generators.SerialNumberGenerator
import DesignPatterns.model.cars.EngineType.EngineType
import DesignPatterns.model.cars.QualityType.QualityType
import DesignPatterns.observer.Subscriber
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

abstract class Car(var engineType: EngineType, var qualityType: QualityType,
                   var maxSpeed: Int, val maxSeatsNumber: Int, var serialNumber: Int = SerialNumberGenerator.generate())
  extends CarPrototype with Tuningable with Subscriber {

  val carType: String = this.getClass.getSimpleName
  var actualSeatsNumber: Int = maxSeatsNumber

  def setSerialNumber(serialNumber: Int): Unit = this.serialNumber = serialNumber

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

  override def getTuningSpeed: Int = 10

  def getMaxSpeed: Int = maxSpeed

  override def toString: String = {
    serialNumber + ": " + carType + " - " + engineType + ", " + qualityType + " quality, " + maxSpeed + " max speed, " + maxSeatsNumber + " seats"
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case other: Car =>
        this.engineType == other.engineType && this.qualityType == other.qualityType &&
          this.maxSpeed == other.maxSpeed && this.maxSeatsNumber == other.maxSeatsNumber &&
          this.carType == other.carType
      case _ =>
        false
    }
  }

  def setQualityType(qualityType: QualityType): Unit = {
    this.qualityType = qualityType
  }

  def getQualityType: QualityType = {
    qualityType
  }

  override def update(): Unit = {
    qualityType match {
      case DesignPatterns.model.cars.QualityType.Low => setQualityType(QualityType.Medium)
      case DesignPatterns.model.cars.QualityType.Medium => setQualityType(QualityType.High)
      case DesignPatterns.model.cars.QualityType.High => sys.error("Car with high quality could not be updated")
    }
  }
}
