package DesignPatterns.builder

import DesignPatterns.model.Car
import DesignPatterns.model.EngineType.EngineType
import DesignPatterns.model.QualityType.QualityType

trait CarBuilder {
  protected var engineType: Option[EngineType] = None
  protected var qualityType: Option[QualityType] = None
  protected var maxSpeed: Option[Int] = None

  def setEngineType(engineType: EngineType): Unit = {
    this.engineType = Some(engineType)
  }

  def setQualityType(qualityType: QualityType): Unit = {
    this.qualityType = Some(qualityType)
  }

  def setMaxSpeed(maxSpeed: Int): Unit = {
    this.maxSpeed = Some(maxSpeed)
  }

  def getResult: Car

  protected def allBasicFieldsDefined: Boolean = {
    engineType.isDefined && qualityType.isDefined && maxSpeed.isDefined
  }
}

class NotAllFieldsDefinedException extends RuntimeException
