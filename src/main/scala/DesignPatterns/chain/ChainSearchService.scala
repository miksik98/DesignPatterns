package DesignPatterns.chain

import DesignPatterns.model.cars.{Car, EngineType, Kombi, Minivan, QualityType, Sedan}

class ChainSearchService(first: ChainCarFinder) {
  def appendFinder(finder: ChainCarFinder): Unit = {
    var current = first
    while (current.next.isDefined) {
      current = current.next.get
    }
    current.next = Some(finder)
  }

  def getCars(filterRequest: FilterRequest): Seq[Car] = {
    first.chainGetCars(filterRequest)
  }
}
