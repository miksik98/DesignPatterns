package DesignPatterns.memento

import DesignPatterns.composite.ProductionFlowComponent
import DesignPatterns.singleton.CarProducer

class CarProducerSnapshot(componentsOriginal: Seq[ProductionFlowComponent]) extends Memento {
  private val components = componentsOriginal.map(c => c.cloneComponent)

  override def restore(): Unit = {
    CarProducer.getInstance().reset()
    components.foreach(c => CarProducer.getInstance().addComponent(c))
  }
}
