package DesignPatterns.factory

import DesignPatterns.model.cars.Car

trait CarFactory {
  def create(): Car
}
