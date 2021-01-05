package DesignPatterns.bridge.services

import DesignPatterns.model.cars.Car
import DesignPatterns.template_method.CarProducerFinder

class CarSearchService(finder: CarProducerFinder) extends SearchService {
  override protected val carFinder: CarProducerFinder = finder

  override def getAll: Seq[Car] = finder.getCars

  override def find(serialNumber: Int): Option[Car] = finder.getCars.find(_.serialNumber == serialNumber)
}
