package DesignPatterns.bridge.finders

import DesignPatterns.model.cars.Car

trait CarFinder {
  protected def getAllCarsFromProducer: Seq[Car]
  def getCars: Seq[Car]
}
