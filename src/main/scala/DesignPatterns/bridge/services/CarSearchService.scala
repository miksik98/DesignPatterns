package DesignPatterns.bridge.services

import DesignPatterns.bridge.finders.CarFinder
import DesignPatterns.model.cars.Car

class CarSearchService(finder: CarFinder) extends SearchService {
  override protected val carFinder: CarFinder = finder

  override def getAll: Seq[Car] = finder.getCars

  override def find(serialNumber: Int): Option[Car] = finder.getCars.find(_.serialNumber == serialNumber)
}
