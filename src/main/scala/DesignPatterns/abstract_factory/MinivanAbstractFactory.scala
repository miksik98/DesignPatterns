package DesignPatterns.abstract_factory
import DesignPatterns.factory.MinivanFactory
import DesignPatterns.flyweight.ManualFactory
import DesignPatterns.model.cars.Car
import DesignPatterns.model.manuals.{BasicManual, MinivanManual}

object MinivanAbstractFactory extends CarAbstractFactory {
  override def createCar(): Car = {
    MinivanFactory.create()
  }

  override def createManual(): BasicManual = {
    ManualFactory.createMinivanManual
  }
}
