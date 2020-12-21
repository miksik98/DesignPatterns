package DesignPatterns.prototype

import DesignPatterns.model.cars.Car

trait CarPrototype {
  def cloneCar(): Car
}
