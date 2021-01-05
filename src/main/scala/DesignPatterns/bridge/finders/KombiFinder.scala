package DesignPatterns.bridge.finders

import DesignPatterns.chain.{ChainCarFinder, FilterName}
import DesignPatterns.chain.FilterName.FilterName
import DesignPatterns.model.cars.{Car, Kombi}

class KombiFinder extends ChainCarFinder {
  override protected val name: FilterName = FilterName.Kombi

  override def filterStrategy(car: Car): Boolean = car.isInstanceOf[Kombi]
}
