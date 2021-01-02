package DesignPatterns.abstract_factory
import DesignPatterns.factory.SedanFactory
import DesignPatterns.model.cars.Car
import DesignPatterns.model.manuals.{BasicManual, SedanManual}

object SedanAbstractFactory extends CarAbstractFactory {
  override def createCar(): Car = {
    SedanFactory.create()
  }

  override def createManual(): BasicManual = {
    new SedanManual
  }
}
