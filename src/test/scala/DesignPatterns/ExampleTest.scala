package DesignPatterns

import org.scalatest.{FunSuite, Matchers }

class ExampleTest extends FunSuite with Matchers {
  test("should increment value"){
    Example.increment(5) shouldBe 6
  }
}
