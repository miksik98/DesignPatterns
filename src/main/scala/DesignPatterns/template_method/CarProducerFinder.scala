package DesignPatterns.template_method

import DesignPatterns.bridge.finders.CarFinder
import DesignPatterns.model.cars.Car
import DesignPatterns.singleton.CarProducer

trait CarProducerFinder extends CarFinder {
  override protected def getAllCarsFromProducer: Seq[Car] = CarProducer.getInstance().getCars
  def filterStrategy(car: Car): Boolean
  def getCars: Seq[Car] = getAllCarsFromProducer.filter(car => filterStrategy(car))
}
