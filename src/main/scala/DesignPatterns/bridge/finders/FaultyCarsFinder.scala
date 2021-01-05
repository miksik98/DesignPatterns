package DesignPatterns.bridge.finders

import DesignPatterns.model.cars.{Car, QualityType}

class FaultyCarsFinder extends CarFinder {
  override def getCars: Seq[Car] = {
    getAllCarsFromProducer.filter(_.getQualityType == QualityType.Low)
  }
}
