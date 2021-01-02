package DesignPatterns.abstract_factory

import DesignPatterns.BasicTest
import DesignPatterns.model.cars.{Kabriolet, Kombi, Minivan, Sedan}
import DesignPatterns.model.manuals.{KabrioletManual, KombiManual, MinivanManual, SedanManual}

class CarAbstractFactoryTest extends BasicTest {
  test("kombi abstract factory test") {
    KombiAbstractFactory.createCar().isInstanceOf[Kombi] shouldBe true
    KombiAbstractFactory.createManual().isInstanceOf[KombiManual] shouldBe true
  }

  test("kabriolet abstract factory test") {
    KabrioletAbstractFactory.createCar().isInstanceOf[Kabriolet] shouldBe true
    KabrioletAbstractFactory.createManual().isInstanceOf[KabrioletManual] shouldBe true
  }

  test("minivan abstract factory test") {
    MinivanAbstractFactory.createCar().isInstanceOf[Minivan] shouldBe true
    MinivanAbstractFactory.createManual().isInstanceOf[MinivanManual] shouldBe true
  }

  test("sedan abstract factory test") {
    SedanAbstractFactory.createCar().isInstanceOf[Sedan] shouldBe true
    SedanAbstractFactory.createManual().isInstanceOf[SedanManual] shouldBe true
  }
}
