package DesignPatterns.visitor

trait ProductionFlowVisitable[T] {
  def accept(visitor: ProductionFlowVisitor[T]): T
}
