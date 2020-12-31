package DesignPatterns.visitor

import DesignPatterns.BasicTest
import DesignPatterns.composite.{FinalProduct, SubContractorGenerator}
import DesignPatterns.model.cars.{Car, EngineType, Kabriolet, Kombi, Minivan, QualityType, Sedan}
import DesignPatterns.singleton.CarProducer

class VisitorTest extends BasicTest {
  private val engineType = EngineType.LPG
  private val qualityType = QualityType.High
  private val maxSpeed = 200

  test("visit subcontractor test") {
    val subContractor = SubContractorGenerator.generate()
    assert(CostProductionFlowVisitor.visit(subContractor) > 0)
    assert(CostProductionFlowVisitor.visit(CarProducer.getInstance()) == 0)
  }

  test("visit kombi test") {
    val trunkCapacity = 70
    val kombi1 = new Kombi(engineType, qualityType, maxSpeed, trunkCapacity)
    val kombi2 = new Kombi(engineType, qualityType, maxSpeed, trunkCapacity * 2)
    compareTwoCars(kombi1, kombi2)
  }

  test("visit kabriolet test") {
    val kabriolet1 = new Kabriolet(engineType, qualityType, maxSpeed, false)
    val kabriolet2 = new Kabriolet(engineType, qualityType, maxSpeed, true)
    compareTwoCars(kabriolet1, kabriolet2)
  }

  test("visit minivan test") {
    val weight = 1000
    val minivan1 = new Minivan(engineType, qualityType, maxSpeed, weight)
    val minivan2 = new Minivan(engineType, qualityType, maxSpeed, weight * 2)
    compareTwoCars(minivan1, minivan2)
  }

  test("visit sedan test") {
    val doors = 2
    val sedan1 = new Sedan(engineType, qualityType, maxSpeed, doors)
    val sedan2 = new Sedan(engineType, qualityType, maxSpeed, doors * 2)
    compareTwoCars(sedan1, sedan2)
  }

  test("sum all costs should be greater than 0") {
    assert(CarProducer.getInstance().calculateCosts() > 0)
  }

  def compareTwoCars[T <: Car](carLowerCost: T, carBiggerCost: T): Unit = {
    assert(CostProductionFlowVisitor.visit(FinalProduct(carLowerCost)) < CostProductionFlowVisitor.visit(FinalProduct(carBiggerCost)))
  }
}
