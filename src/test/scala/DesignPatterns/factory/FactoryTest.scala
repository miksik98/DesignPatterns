package DesignPatterns.factory

import DesignPatterns.BasicTest
import DesignPatterns.model.{Kabriolet, Kombi, Minivan, Sedan}

class FactoryTest extends BasicTest {
  test("should create kabriolet") {
    KabrioletFactory.create().isInstanceOf[Kabriolet] shouldBe true
  }

  test("should create kombi") {
    KombiFactory.create().isInstanceOf[Kombi] shouldBe true
  }

  test("should create minivan") {
    MinivanFactory.create().isInstanceOf[Minivan] shouldBe true
  }

  test("should create sedan") {
    SedanFactory.create().isInstanceOf[Sedan] shouldBe true
  }
}
