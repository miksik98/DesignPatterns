package DesignPatterns.bridge.finders

import DesignPatterns.chain.{ChainCarFinder, FilterName}
import DesignPatterns.chain.FilterName.FilterName
import DesignPatterns.model.cars.{Car, QualityType}

class FaultyCarsFinder extends ChainCarFinder {
  override def getCars: Seq[Car] = {
    getAllCarsFromProducer.filter(_.getQualityType == QualityType.Low)
  }

  override protected val name: FilterName = FilterName.Faulty
}
