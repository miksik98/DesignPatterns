package DesignPatterns.strategy

import DesignPatterns.model.cars.{Car, QualityType}

object SuperImproveStrategy extends ImprovingStrategy {
  override def execute(car: Car): Unit = {
    car.getQualityType match {
      case DesignPatterns.model.cars.QualityType.Low => car.setQualityType(QualityType.High)
      case DesignPatterns.model.cars.QualityType.Medium => car.setQualityType(QualityType.High)
      case DesignPatterns.model.cars.QualityType.High => sys.error("Car with high quality could not be updated")
    }
  }
}
