package DesignPatterns.singleton

import DesignPatterns.composite.{FinalProduct, ProductionFlowComponent, SubContractor, SubContractorGenerator}
import DesignPatterns.model.cars.Car

protected class CarProducer(var components: Seq[ProductionFlowComponent])
  extends SubContractor(components, isMainContractor = true) {

  def this() = {
    this(Seq.empty)
  }

  def deleteCar(serialNumber: Int): Unit = {
    def branchContainsCar(component: ProductionFlowComponent): Boolean = {
      component match {
        case fp: FinalProduct => fp.hasSerialNumber(serialNumber)
        case sc: SubContractor =>
          for (c <- sc.getComponents) {
            if (branchContainsCar(c)) {
              return true
            }
          }
          false
      }
    }

    components.find(branchContainsCar) match {
      case Some(component) =>
        components = components.filterNot(c => c == component)
      case _ =>
        throw new CarNotFoundException(serialNumber)
    }
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

class CarNotFoundException(serialNumber: Int) extends RuntimeException

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
