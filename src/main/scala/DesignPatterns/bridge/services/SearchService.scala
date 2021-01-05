package DesignPatterns.bridge.services

import DesignPatterns.bridge.finders.CarFinder
import DesignPatterns.model.cars.Car

trait SearchService {
  protected val carFinder: CarFinder

  def getAll: Seq[Car]
  def find(serialNumber: Int): Option[Car]
}
