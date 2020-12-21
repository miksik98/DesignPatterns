package DesignPatterns.prototype

import DesignPatterns.factory.{KabrioletFactory, KombiFactory, MinivanFactory, SedanFactory}
import org.scalatest.{FunSuite, Matchers}

class PrototypeTest extends FunSuite with Matchers {

  test("clone kabriolet") {
    val kabriolet = KabrioletFactory.create()
    val clone = kabriolet.cloneCar()
    kabriolet shouldEqual clone
    assert(!kabriolet.eq(clone))
  }

  test("clone kombi") {
    val kombi = KombiFactory.create()
    val clone = kombi.cloneCar()
    kombi shouldEqual clone
    assert(!kombi.eq(clone))
  }

  test("clone minivan") {
    val minivan = MinivanFactory.create()
    val clone = minivan.cloneCar()
    minivan shouldEqual clone
    assert(!minivan.eq(clone))
  }

  test("clone sedan") {
    val sedan = SedanFactory.create()
    val clone = sedan.cloneCar()
    sedan shouldEqual clone
    assert(!sedan.eq(clone))
  }

}
