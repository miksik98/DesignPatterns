package DesignPatterns.bridge.finders

import DesignPatterns.chain.{ChainCarFinder, FilterName}
import DesignPatterns.chain.FilterName.FilterName
import DesignPatterns.model.cars.{Car, QualityType}

class FaultyCarsFinder extends ChainCarFinder {
  override protected val name: FilterName = FilterName.Faulty

  override def filterStrategy(car: Car): Boolean = car.getQualityType == QualityType.Low
}
