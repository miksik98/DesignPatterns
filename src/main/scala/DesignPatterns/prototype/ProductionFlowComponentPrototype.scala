package DesignPatterns.prototype

import DesignPatterns.composite.ProductionFlowComponent

trait ProductionFlowComponentPrototype {
  def cloneComponent: ProductionFlowComponent
}
