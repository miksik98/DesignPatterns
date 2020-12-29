package DesignPatterns.singleton

import DesignPatterns.composite.{FinalProduct, SubContractor}

protected class CarProducer
  extends SubContractor(Seq.empty, isMainContractor = true) {

  def deleteCar(serialNumber: Int): Unit = {
    val it = createIterator()
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

  def printCars(): Unit = {
    getCars.foreach(println(_))
  }

  def carsNumber: Int = getCars.length
}

class CarNotFoundException(serialNumber: Int) extends RuntimeException

object CarProducer {
  private var instance: Option[CarProducer] = None

  def getInstance(): CarProducer = {
    instance match {
      case Some(instance) => instance
      case None =>
        instance = Some(new CarProducer())
        instance.get
    }
  }
}
