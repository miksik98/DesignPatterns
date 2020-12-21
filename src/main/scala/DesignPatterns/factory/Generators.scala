package DesignPatterns.factory

import DesignPatterns.model.cars.EngineType.EngineType
import DesignPatterns.model.cars.{EngineType, QualityType}
import DesignPatterns.model.cars.QualityType.QualityType

import scala.util.Random

object Generators {

  trait Generator[T] {
    val rand = new Random
    def generate(): T
  }

  object QualityGenerator extends Generator[QualityType] {
    def generate(): QualityType = {
      val x = rand.nextInt(10)
      if (x > 5) {
        QualityType.High
      } else if (x > 2) {
        QualityType.Medium
      } else {
        QualityType.Low
      }
    }
  }

  object EngineGenerator extends Generator[EngineType] {
    def generate(): EngineType = {
      if (rand.nextInt(10) > 4) {
        EngineType.Diesel
      } else {
        EngineType.LPG
      }
    }
  }

  object ExtraSpeedGenerator extends Generator[Int] {
    override def generate(): Int = {
      rand.nextInt(100)
    }
  }

  object RoofExistenceGenerator extends Generator[Boolean] {
    override def generate(): Boolean = {
      rand.nextBoolean()
    }
  }

  object WeightGenerator extends Generator[Int] {
    override def generate(): Int = {
      (rand.nextInt() % 4 + 1) * 1000
    }
  }

  object DoorsNumberGenerator extends Generator[Int] {
    override def generate(): Int = {
      if (rand.nextBoolean()) {
        2
      } else {
        4
      }
    }
  }

  object BootCapacityGenerator extends Generator[Int] {
    override def generate(): Int = {
      rand.nextInt(300) + 300
    }
  }
}