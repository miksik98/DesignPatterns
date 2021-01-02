package DesignPatterns.singleton

import DesignPatterns.composite.{FinalProduct, SubContractor}
import DesignPatterns.memento.{CarProducerHistory, CarProducerSnapshot, Memento, Originator}
import DesignPatterns.model.cars.{Car, QualityType}
import DesignPatterns.observer.CarQualityImprover
import DesignPatterns.visitor.CostProductionFlowVisitor

protected class CarProducer
  extends SubContractor(Seq.empty, isMainContractor = true) with Originator {

  private val qualityImprover = new CarQualityImprover

  override def addCar(car: Car): Unit = {
    super.addCar(car)
    if (car.qualityType != QualityType.High) {
      qualityImprover.addSubscriber(car)
    }
  }

  override def reset(): Unit = {
    super.reset()
    qualityImprover.subscribers = Seq.empty
  }

  def refreshQualityImprover(): Unit = {
    getCars.foreach(
      car =>
        if (!qualityImprover.subscribers.contains(car) && car.qualityType != QualityType.High) {
          qualityImprover.addSubscriber(car)
        }
    )
  }

  def calculateCosts(): Int = {
    val it = createIterator()
    var costsSum = 0
    while (it.hasNext) {
      costsSum += CostProductionFlowVisitor.visit(it.next)
    }
    costsSum
  }

  def improveAllCarsQuality(): Unit = {
    qualityImprover.notifySubscribers()
  }

  def deleteCar(serialNumber: Int): Unit = {
    val it = createIterator()
    while (it.hasNext) {
      it.next match {
        case fp: FinalProduct if fp.hasSerialNumber(serialNumber) =>
          fp.removeFromSubContractor()
          return
        case _ =>
      }
    }
    throw new CarNotFoundException(serialNumber)
  }

  def printCars(): Unit = {
    getCars.foreach(println(_))
  }

  def carsNumber: Int = getCars.length

  override def save(): CarProducerSnapshot = {
    new CarProducerSnapshot(components)
  }
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
