package DesignPatterns.strategy

import DesignPatterns.model.cars.Car

trait ImprovingStrategy {
  def execute(car: Car): Unit
}
