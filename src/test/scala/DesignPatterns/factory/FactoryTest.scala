package DesignPatterns.factory

import DesignPatterns.model.{Kabriolet, Kombi, Minivan, Sedan}
import org.scalatest.{FunSuite, Matchers}

class FactoryTest extends FunSuite with Matchers {
  test("should create kabriolet") {
    val factory = new KabrioletFactory
    factory.create().isInstanceOf[Kabriolet] shouldBe true
  }

  test("should create kombi") {
    val factory = new KombiFactory
    factory.create().isInstanceOf[Kombi] shouldBe true
  }

  test("should create minivan") {
    val factory = new MinivanFactory
    factory.create().isInstanceOf[Minivan] shouldBe true
  }

  test("should create sedan") {
    val factory = new SedanFactory
    factory.create().isInstanceOf[Sedan] shouldBe true
  }
}
