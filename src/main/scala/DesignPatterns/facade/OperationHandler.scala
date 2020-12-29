package DesignPatterns.facade

import DesignPatterns.composite.{FinalProduct, SubContractor}
import DesignPatterns.factory.{KabrioletFactory, KombiFactory, MinivanFactory, SedanFactory}
import DesignPatterns.model.cars.Car
import DesignPatterns.singleton.CarProducer

trait OperationHandler {
  def createKabriolet(): Car
  def createKombi(): Car
  def createMinivan(): Car
  def createSedan(): Car
  def createCar(car: Car): Unit
  def findCar(serialNumber: Int): Option[FinalProduct]
  def createCarWithSubContractor(subContractor: SubContractor, car: Car): Unit
  def deleteCar(serialNumber: Int): Unit
  def printCreatedCars(): Unit
  def printSubContractorTree(): Unit
}

class BasicOperationHandler extends OperationHandler {
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

  override def printCreatedCars(): Unit = {
    CarProducer.getInstance().printCars()
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
}

class SubContractorNotFound(subContractor: SubContractor) extends RuntimeException
