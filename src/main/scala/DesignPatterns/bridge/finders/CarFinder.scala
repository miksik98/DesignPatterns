package DesignPatterns.bridge.finders

import DesignPatterns.model.cars.Car
import DesignPatterns.singleton.CarProducer

trait CarFinder {

  protected def getAllCarsFromProducer: Seq[Car] = CarProducer.getInstance().getCars

  def getCars: Seq[Car]
}
