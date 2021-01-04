package DesignPatterns.state
import DesignPatterns.composite.FinalProduct
import DesignPatterns.singleton.{CarNotFoundException, CarProducer}

object Admin extends CarProducerState {
  override def deleteCar(serialNumber: Int): Unit = {
    val it = CarProducer.getInstance().createIterator()
    while (it.hasNext) {
      it.next match {
        case fp: FinalProduct if fp.hasSerialNumber(serialNumber) =>
          fp.removeFromSubContractor()
          return
        case _ =>
      }
    }
    throw new CarNotFoundException(serialNumber)
  }
}
