package DesignPatterns.facade

import DesignPatterns.composite.FinalProduct
import DesignPatterns.factory.{KabrioletFactory, KombiFactory, MinivanFactory, SedanFactory}
import DesignPatterns.model.cars.Car
import DesignPatterns.singleton.CarProducer

trait OperationHandler {
  def createKabriolet(): Unit
  def createKombi(): Unit
  def createMinivan(): Unit
  def createSedan(): Unit
  def deleteCar(serialNumber: Int): Unit
  def printCreatedCars(): Unit
  def printSubContractorTree(): Unit
}

class BasicOperationHandler extends OperationHandler {
  override def createKabriolet(): Unit = {
    KabrioletFactory.create()
  }

  override def createKombi(): Unit = {
    KombiFactory.create()
  }

  override def createMinivan(): Unit = {
    MinivanFactory.create()
  }

  override def createSedan(): Unit = {
    SedanFactory.create()
  }

  override def deleteCar(serialNumber: Int): Unit = {
    CarProducer.getInstance().deleteCar(serialNumber)
  }

  override def printCreatedCars(): Unit = {
    CarProducer.getInstance().printCars()
  }

  override def printSubContractorTree(): Unit = {
    CarProducer.getInstance().printTree()
  }
}
