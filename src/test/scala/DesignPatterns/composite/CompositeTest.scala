package DesignPatterns.composite

import DesignPatterns.BasicTest
import DesignPatterns.factory.Generators._
import DesignPatterns.singleton.CarProducer

class CompositeTest extends BasicTest {

  test("should throw exception while initialization subcontractor with empty seq") {
    assertThrows[IllegalArgumentException](new SubContractor(Seq.empty))
  }

  private val cars = Seq (
    CarGenerator.generate(),
    CarGenerator.generate(),
    CarGenerator.generate(),
    CarGenerator.generate()
  )

  test("getCars should return all leaves") {
    val subBuilder = new SubContractorBuilder
    subBuilder.withComponents(
      Seq(
        FinalProduct(cars(0)),
        FinalProduct(cars(1))
      )
    )

    val builderMain = new SubContractorBuilder
    builderMain.withComponents(
      Seq(
        subBuilder.getResult,
        FinalProduct(cars(2)),
        FinalProduct(cars(3))
      )
    )

    builderMain.getResult.getCars shouldBe cars
  }

  test("CarProducer test") {
    CarProducer.getInstance().reset()
    CarProducer.getInstance().addCar(cars(0))
    CarProducer.getInstance().addCar(cars(1))
    CarProducer.getInstance().addComponent(
      new SubContractor(Seq(FinalProduct(cars(2)), FinalProduct(cars(3))))
    )
    CarProducer.getInstance().getCars shouldBe cars
  }
}
