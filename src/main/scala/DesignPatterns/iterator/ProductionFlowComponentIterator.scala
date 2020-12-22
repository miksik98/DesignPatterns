package DesignPatterns.iterator

import DesignPatterns.composite.{FinalProduct, ProductionFlowComponent, SubContractor}

import scala.collection.immutable.Queue

class ProductionFlowComponentIterator(component: ProductionFlowComponent) extends Iterator[ProductionFlowComponent] {
  var componentsQueue: Queue[ProductionFlowComponent] = Queue(component)

  override def next: ProductionFlowComponent = {
    val dequeued = componentsQueue.dequeue
    componentsQueue = dequeued._2
    dequeued._1 match {
      case that: SubContractor =>
          that.getComponents.foreach(x => componentsQueue = componentsQueue.appended(x))
      case _ =>
    }
    dequeued._1
  }

  override def hasNext: Boolean = componentsQueue.nonEmpty
}