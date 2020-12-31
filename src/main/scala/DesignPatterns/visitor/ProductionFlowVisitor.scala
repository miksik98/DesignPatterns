package DesignPatterns.visitor

import DesignPatterns.composite.ProductionFlowComponent

trait ProductionFlowVisitor[T] {
  def visit(productionFlowComponent: ProductionFlowComponent): T
}
