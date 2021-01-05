package DesignPatterns.bridge.finders

import DesignPatterns.model.cars.Car

class SpeedCarsFinder(lowerBound: Int, higherBound: Int) extends CarFinder {
  if (lowerBound > higherBound) {
    sys.error("lowerBound is higher than higherBound")
  }

  override def getCars: Seq[Car] = {
    getAllCarsFromProducer.filter(car => car.getMaxSpeed >= lowerBound && car.getMaxSpeed <= higherBound)
  }
}
