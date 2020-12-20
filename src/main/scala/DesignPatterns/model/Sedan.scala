package DesignPatterns.model
import DesignPatterns.model.EngineType.EngineType
import DesignPatterns.model.QualityType.QualityType

class Sedan(engineType: EngineType, qualityType: QualityType, maxSpeed: Int)
  extends Car(engineType, qualityType, maxSpeed, 5)