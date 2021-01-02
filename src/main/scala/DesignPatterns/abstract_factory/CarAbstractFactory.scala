package DesignPatterns.abstract_factory

import DesignPatterns.model.cars.Car
import DesignPatterns.model.manuals.BasicManual

trait CarAbstractFactory {
  def createCar(): Car
  def createManual(): BasicManual
}
