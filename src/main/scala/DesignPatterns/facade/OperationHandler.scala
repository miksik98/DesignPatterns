package DesignPatterns.facade

import DesignPatterns.composite.{FinalProduct, SubContractor}
import DesignPatterns.factory.{KabrioletFactory, KombiFactory, MinivanFactory, SedanFactory}
import DesignPatterns.memento.{CarProducerHistory, CarProducerSnapshot}
import DesignPatterns.model.cars.Car
import DesignPatterns.singleton.CarProducer

trait TreeOperationHandler {
  def printSubContractorTree(): Unit
  def calculateCosts(): Int
}

trait StateOperationHandler {
  def saveState(state: CarProducerSnapshot = CarProducer.getInstance().save()): Unit
  def restoreState(): Unit
  def removeLastState(): Unit
}

trait ImproveOperationHandler {
  def improveFaultyCars(): Unit
  def getCreatedCars: Seq[Car]
}

trait CreateOperationHandler {
  def createKabriolet(): Car
  def createKombi(): Car
  def createMinivan(): Car
  def createSedan(): Car
  def createCar(car: Car): Unit
  def deleteCar(serialNumber: Int): Unit
  def findCar(serialNumber: Int): Option[FinalProduct]
  def createCarWithSubContractor(subContractor: SubContractor, car: Car): Unit
}

class BasicOperationHandler extends TreeOperationHandler with CreateOperationHandler with ImproveOperationHandler
  with StateOperationHandler {
  override def createKabriolet(): Car = {
    KabrioletFactory.create()
  }

  override def createKombi(): Car = {
    KombiFactory.create()
  }

  override def createMinivan(): Car = {
    MinivanFactory.create()
  }

  override def createSedan(): Car = {
    SedanFactory.create()
  }

  override def deleteCar(serialNumber: Int): Unit = {
    CarProducer.getInstance().deleteCar(serialNumber)
  }

  override def findCar(serialNumber: Int): Option[FinalProduct] = {
    val it = CarProducer.getInstance().createIterator()
    while (it.hasNext) {
      it.next match {
        case product: FinalProduct if product.hasSerialNumber(serialNumber) => return Some(product)
        case _ =>
      }
    }
    None
  }

  def printCreatedCars(): Unit = {
    CarProducer.getInstance().printCars()
  }

  def getCreatedCars: Seq[Car] = {
    CarProducer.getInstance().refreshQualityImprover()
    CarProducer.getInstance().getCars
  }

  override def printSubContractorTree(): Unit = {
    CarProducer.getInstance().printTree()
  }

  override def createCar(car: Car): Unit = {
    CarProducer.getInstance().addCar(car)
  }

  override def createCarWithSubContractor(subContractor: SubContractor, car: Car): Unit = {
    val it = CarProducer.getInstance().createIterator()
    while (it.hasNext) {
      it.next match {
        case contractor: SubContractor if contractor == subContractor =>
          contractor.addCar(car)
          return
        case _ =>
      }
    }
    throw new SubContractorNotFound(subContractor)
  }

  override def improveFaultyCars(): Unit = {
    CarProducer.getInstance().improveAllCarsQuality()
  }

  override def calculateCosts(): Int = {
    CarProducer.getInstance().calculateCosts()
  }

  override def saveState(state: CarProducerSnapshot = CarProducer.getInstance().save()): Unit = {
    CarProducerHistory.makeBackup(state)
  }

  override def restoreState(): Unit = {
    CarProducerHistory.restore()
  }

  override def removeLastState(): Unit = {
    CarProducerHistory.removeLastSaved()
  }
}

class SubContractorNotFound(subContractor: SubContractor) extends RuntimeException
