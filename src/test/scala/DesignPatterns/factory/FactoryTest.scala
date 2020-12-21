package DesignPatterns.factory

import DesignPatterns.model.{Kabriolet, Kombi, Minivan, Sedan}
import org.scalatest.{FunSuite, Matchers}

class FactoryTest extends FunSuite with Matchers {
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
