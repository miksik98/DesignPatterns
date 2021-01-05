package DesignPatterns.chain

import DesignPatterns.bridge.finders.CarFinder
import DesignPatterns.chain.FilterName.FilterName
import DesignPatterns.model.cars.Car

trait ChainCarFinder extends CarFinder {
  var next: Option[ChainCarFinder] = None
  protected val name: FilterName

  def chainGetCars(filterRequest: FilterRequest): Seq[Car] = {
    val cars = if (filterRequest.filtersMap.get(name)) {
      getCars
    } else {
      getAllCarsFromProducer
    }

    next match {
      case Some(finder) => cars.filter(x => finder.chainGetCars(filterRequest).contains(x))
      case None => cars
    }
  }
}
