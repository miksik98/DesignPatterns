package DesignPatterns.factory

import DesignPatterns.model.{Car, EngineType, QualityType}

trait CarFactory {
  def create(): Car
}
