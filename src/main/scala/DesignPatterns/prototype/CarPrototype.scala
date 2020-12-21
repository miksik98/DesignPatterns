package DesignPatterns.prototype

import DesignPatterns.model.Car

trait CarPrototype {
  def cloneCar(): Car
}
