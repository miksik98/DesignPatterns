package DesignPatterns.adapter

import DesignPatterns.builder.KombiBuilder
import DesignPatterns.factory.{KabrioletFactory, KombiFactory, MinivanFactory, SedanFactory}
import DesignPatterns.model.Cargo
import DesignPatterns.model.cars.{EngineType, Kabriolet, Kombi, Minivan, QualityType, Sedan}
import org.scalatest.{FunSuite, Matchers}

class AdapterTest extends FunSuite with Matchers {

  test("kombi trunk test") {
    val cargo1 = new Cargo(200)
    val cargo2 = new Cargo(400)
    val kombiBuilder = new KombiBuilder
    kombiBuilder.setQualityType(QualityType.High)
    kombiBuilder.setEngineType(EngineType.LPG)
    kombiBuilder.setMaxSpeed(200)
    kombiBuilder.setTrunkCapacity(200)
    val kombi = kombiBuilder.getResult.asInstanceOf[Kombi]
    cargo1.fitsTo(kombi) shouldBe true
    cargo2.fitsTo(kombi) shouldBe false
    kombi.takeAwaySeats(2)
    cargo2.fitsTo(kombi) shouldBe true
    kombi.putSeats(1)
    cargo2.fitsTo(kombi) shouldBe false
  }

  test("kabriolet trunk test") {
    val cargo = new Cargo(100)
    val kabriolet = KabrioletFactory.create().asInstanceOf[Kabriolet]
    val kabrioletTrunkAdapter = new KabrioletTrunkAdapter(kabriolet)
    cargo.fitsTo(kabrioletTrunkAdapter) shouldBe false
    kabriolet.takeAwaySeats(1)
    cargo.fitsTo(kabrioletTrunkAdapter) shouldBe true
  }

  test("minivan trunk test") {
    val cargo = new Cargo(100)
    val minivan = MinivanFactory.create().asInstanceOf[Minivan]
    val minivanTrunkAdapter = new MinivanTrunkAdapter(minivan)
    cargo.fitsTo(minivanTrunkAdapter) shouldBe false
    minivan.takeAwaySeats(1)
    cargo.fitsTo(minivanTrunkAdapter) shouldBe true
  }

  test("sedan trunk test") {
    val cargo = new Cargo(100)
    val sedan = SedanFactory.create().asInstanceOf[Sedan]
    val sedanTrunkAdapter = new SedanTrunkAdapter(sedan)
    cargo.fitsTo(sedanTrunkAdapter) shouldBe false
    sedan.takeAwaySeats(1)
    cargo.fitsTo(sedanTrunkAdapter) shouldBe true
  }

}
