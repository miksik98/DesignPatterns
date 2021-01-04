package DesignPatterns.memento

import DesignPatterns.composite.FinalProduct
import DesignPatterns.factory.SedanFactory
import DesignPatterns.singleton.CarProducer
import DesignPatterns.state.Admin
import org.scalatest.{BeforeAndAfterAll, FunSuite, Matchers}

class MementoTest extends FunSuite with Matchers with BeforeAndAfterAll {
  CarProducer.getInstance().reset()
  CarProducer.getInstance().setState(Admin)
  private val sedan = SedanFactory.create()
  private val component = FinalProduct(sedan, CarProducer.getInstance())
  private val snapshot = new CarProducerSnapshot(Seq(component))

  test("restoring snapshot test") {
    CarProducer.getInstance().carsNumber shouldBe 1
    CarProducer.getInstance().deleteCar(sedan.serialNumber)
    CarProducer.getInstance().carsNumber shouldBe 0
    snapshot.restore()
    CarProducer.getInstance().getCars shouldBe Seq(sedan)
  }

  test("car producer history test") {
    CarProducerHistory.makeBackup(snapshot)
    CarProducer.getInstance().deleteCar(sedan.serialNumber)
    CarProducer.getInstance().carsNumber shouldBe 0
    CarProducerHistory.restore()
    CarProducer.getInstance().getCars shouldBe Seq(sedan)
  }
}
