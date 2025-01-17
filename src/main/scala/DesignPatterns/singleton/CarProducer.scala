package DesignPatterns.singleton

import DesignPatterns.composite.{FinalProduct, SubContractor}
import DesignPatterns.mediator.CommunicationMediator
import DesignPatterns.memento.{CarProducerSnapshot, Originator}
import DesignPatterns.model.cars.{Car, QualityType}
import DesignPatterns.observer.CarQualityImprover
import DesignPatterns.state.{CarProducerState, Moderator}
import DesignPatterns.strategy.{ImprovingStrategy, SimpleImproveStrategy}
import DesignPatterns.visitor.CostProductionFlowVisitor

protected class CarProducer
  extends SubContractor(Seq.empty, isMainContractor = true) with Originator with CommunicationMediator {

  private val qualityImprover = new CarQualityImprover
  private var state: CarProducerState = Moderator

  override def addCar(car: Car): Unit = {
    super.addCar(car)
    if (car.qualityType != QualityType.High) {
      qualityImprover.addSubscriber(car)
    }
  }

  def setState(state: CarProducerState): Unit = {
    this.state = state
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

  def improveAllCarsQuality(improvingStrategy: ImprovingStrategy = SimpleImproveStrategy): Unit = {
    qualityImprover.notifySubscribers(improvingStrategy)
  }

  def deleteCar(serialNumber: Int): Unit = {
    state.deleteCar(serialNumber)
  }

  def printCars(): Unit = {
    getCars.foreach(println(_))
  }

  def carsNumber: Int = getCars.length

  override def save(): CarProducerSnapshot = {
    new CarProducerSnapshot(components)
  }

  override def forwardMessage(serialNumber: Int, message: String): Unit = {
    getCars
      .find(_.serialNumber == serialNumber)
      .getOrElse(throw new CarNotFoundException(serialNumber))
      .addMessage(message)
  }
}

class CarNotFoundException(serialNumber: Int) extends RuntimeException
class InsufficientPrivileges extends RuntimeException

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
