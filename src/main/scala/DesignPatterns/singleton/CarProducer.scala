package DesignPatterns.singleton

import DesignPatterns.composite.{FinalProduct, ProductionFlowComponent, SubContractor, SubContractorGenerator}
import DesignPatterns.model.cars.Car

protected class CarProducer(var components: Seq[ProductionFlowComponent])
  extends SubContractor(components, isMainContractor = true) {
  def this() = {
    this(Seq.empty)
  }

  override def getComponents: Seq[ProductionFlowComponent] = components

  def printCars(): Unit = {
    getCars.foreach(println(_))
  }

  def addComponent(component: ProductionFlowComponent): Unit = this.synchronized {
    components = components :+ component
  }

  def addCar(car: Car): Unit = this.synchronized {
    components = components :+ FinalProduct(car)
  }

  def reset(): Unit = this.synchronized {
    components = Seq.empty
  }

  def carsNumber: Int = getCars.length
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
