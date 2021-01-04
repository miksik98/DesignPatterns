package DesignPatterns.abstract_factory
import DesignPatterns.factory.KombiFactory
import DesignPatterns.flyweight.ManualFactory
import DesignPatterns.model.cars.Car
import DesignPatterns.model.manuals.{BasicManual, KombiManual}

object KombiAbstractFactory extends CarAbstractFactory {
  override def createCar(): Car = {
    KombiFactory.create()
  }

  override def createManual(): BasicManual = {
    ManualFactory.createKombiManual
  }
}
