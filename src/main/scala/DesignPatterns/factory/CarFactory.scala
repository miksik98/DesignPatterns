package DesignPatterns.factory

import DesignPatterns.model.EngineType.EngineType
import DesignPatterns.model.{Car, EngineType, QualityType}
import DesignPatterns.model.QualityType.QualityType

import scala.util.Random

trait CarFactory {
  def create(): Car
}

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