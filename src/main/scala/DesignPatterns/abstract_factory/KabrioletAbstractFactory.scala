package DesignPatterns.abstract_factory
import DesignPatterns.factory.KabrioletFactory
import DesignPatterns.flyweight.ManualFactory
import DesignPatterns.model.cars.Car
import DesignPatterns.model.manuals.{BasicManual, KabrioletManual}

object KabrioletAbstractFactory extends CarAbstractFactory {
  override def createCar(): Car = {
    KabrioletFactory.create()
  }

  override def createManual(): BasicManual = {
    ManualFactory.createKabrioletManual
  }
}
