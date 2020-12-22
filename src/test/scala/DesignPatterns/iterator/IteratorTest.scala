package DesignPatterns.iterator

import DesignPatterns.BasicTest
import DesignPatterns.composite.{FinalProduct, SubContractor, SubContractorGenerator}
import DesignPatterns.factory.Generators.CarGenerator
import DesignPatterns.model.cars.Car
import DesignPatterns.singleton.CarProducer

class IteratorTest extends BasicTest {

  test("use iterator to find all cars") {
    CarProducer.getInstance().addComponent(
      SubContractorGenerator.generate()
    )

    val it = CarProducer.getInstance().createIterator()
    var cars: Seq[Car] = Seq.empty
    while (it.hasNext) {
      it.next match {
        case fp: FinalProduct => cars = cars ++ fp.getCars
        case _ =>
      }
    }
    cars shouldBe CarProducer.getInstance().getCars
  }
}
