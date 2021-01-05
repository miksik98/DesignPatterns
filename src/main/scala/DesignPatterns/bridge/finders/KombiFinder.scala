package DesignPatterns.bridge.finders

import DesignPatterns.model.cars.{Car, Kombi}

class KombiFinder extends CarFinder {
  override def getCars: Seq[Car] = {
    getAllCarsFromProducer.filter(_.isInstanceOf[Kombi])
  }
}
