package DesignPatterns.singleton

import DesignPatterns.model.Car

protected class CarProducer {
  def printCars(): Unit = {
    cars.foreach(println(_))
  }

  private var cars: Seq[Car] = Seq.empty

  def addCar(car: Car): Unit = cars = this.synchronized {
    cars :+ car
  }

  def reset(): Unit = cars = Seq.empty

  def carsNumber: Int = cars.length
}

object CarProducer {
  private var instance: Option[CarProducer] = None

  def getInstance(): CarProducer = {
    instance match {
      case Some(instance) => instance
      case None =>
        instance = Some(new CarProducer())
        instance.get
    }
  }
}
