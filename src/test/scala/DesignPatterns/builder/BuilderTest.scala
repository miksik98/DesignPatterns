package DesignPatterns.builder

import DesignPatterns.BasicTest
import DesignPatterns.model.{EngineType, Kabriolet, Kombi, Minivan, QualityType, Sedan}

class BuilderTest extends BasicTest {

  private val engineType = EngineType.LPG
  private val maxSpeed = 200
  private val qualityType = QualityType.High

  test("kabriolet builder test") {
    val builder = new KabrioletBuilder
    builder.setEngineType(engineType)
    builder.setMaxSpeed(maxSpeed)
    builder.setQualityType(qualityType)
    builder.addRoof()
    val car = builder.getResult
    car.isInstanceOf[Kabriolet] shouldBe true
    car.maxSpeed shouldBe maxSpeed
    car.qualityType shouldBe qualityType
    car.engineType shouldBe engineType
    car.asInstanceOf[Kabriolet].hasRoof shouldBe true
  }

  test("kombi builder test"){
    val builder = new KombiBuilder
    val bootCapacity = 400
    builder.setEngineType(engineType)
    builder.setMaxSpeed(maxSpeed)
    builder.setQualityType(qualityType)
    builder.setBootCapacity(bootCapacity)
    val car = builder.getResult
    car.isInstanceOf[Kombi] shouldBe true
    car.maxSpeed shouldBe maxSpeed
    car.qualityType shouldBe qualityType
    car.engineType shouldBe engineType
    car.asInstanceOf[Kombi].bootCapacity shouldBe bootCapacity
  }

  test("minivan builder test") {
    val builder = new MinivanBuilder
    val weight = 2000
    builder.setEngineType(engineType)
    builder.setMaxSpeed(maxSpeed)
    builder.setQualityType(qualityType)
    builder.setWeight(weight)
    val car = builder.getResult
    car.isInstanceOf[Minivan] shouldBe true
    car.maxSpeed shouldBe maxSpeed
    car.qualityType shouldBe qualityType
    car.engineType shouldBe engineType
    car.asInstanceOf[Minivan].weight shouldBe weight
  }

  test("sedan builder test") {
    val builder = new SedanBuilder
    val doorsNumber = 4
    builder.setEngineType(engineType)
    builder.setMaxSpeed(maxSpeed)
    builder.setQualityType(qualityType)
    builder.setDoorsNumber(doorsNumber)
    val car = builder.getResult
    car.isInstanceOf[Sedan] shouldBe true
    car.maxSpeed shouldBe maxSpeed
    car.qualityType shouldBe qualityType
    car.engineType shouldBe engineType
    car.asInstanceOf[Sedan].doorsNumber shouldBe doorsNumber
  }

  test("should throw exception when not all specification defined") {
    val builder = new KabrioletBuilder
    builder.setEngineType(engineType)
    builder.setMaxSpeed(maxSpeed)
    builder.setQualityType(qualityType)
    assertThrows[NotAllFieldsDefinedException](builder.getResult)
  }
}
