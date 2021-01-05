package DesignPatterns.bridge.finders

import DesignPatterns.chain.{ChainCarFinder, FilterName}
import DesignPatterns.chain.FilterName.FilterName
import DesignPatterns.model.cars.{Car, Kombi}

class KombiFinder extends ChainCarFinder {
  override def getCars: Seq[Car] = {
    getAllCarsFromProducer.filter(_.isInstanceOf[Kombi])
  }

  override protected val name: FilterName = FilterName.Kombi
}
