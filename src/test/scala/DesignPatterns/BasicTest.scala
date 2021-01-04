package DesignPatterns

import DesignPatterns.singleton.CarProducer
import DesignPatterns.state.Admin
import org.scalatest.{BeforeAndAfterAll, FunSuite, Matchers}

abstract class BasicTest extends FunSuite with Matchers with BeforeAndAfterAll {
  override def beforeAll(): Unit = {
    CarProducer.getInstance().reset()
    CarProducer.getInstance().setState(Admin)
  }
}
