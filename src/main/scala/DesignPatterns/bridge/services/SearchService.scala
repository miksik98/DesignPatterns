package DesignPatterns.bridge.services

import DesignPatterns.model.cars.Car
import DesignPatterns.template_method.CarProducerFinder

trait SearchService {
  protected val carFinder: CarProducerFinder

  def getAll: Seq[Car]
  def find(serialNumber: Int): Option[Car]
}
