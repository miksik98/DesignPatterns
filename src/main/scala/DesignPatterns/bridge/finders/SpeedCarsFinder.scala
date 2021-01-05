package DesignPatterns.bridge.finders

import DesignPatterns.chain.{ChainCarFinder, FilterName}
import DesignPatterns.chain.FilterName.FilterName
import DesignPatterns.model.cars.Car

class SpeedCarsFinder(lowerBound: Int, higherBound: Int) extends ChainCarFinder {
  if (lowerBound > higherBound) {
    sys.error("lowerBound is higher than higherBound")
  }

  override def getCars: Seq[Car] = {
    getAllCarsFromProducer.filter(car => car.getMaxSpeed >= lowerBound && car.getMaxSpeed <= higherBound)
  }

  override protected val name: FilterName = FilterName.Speed
}
