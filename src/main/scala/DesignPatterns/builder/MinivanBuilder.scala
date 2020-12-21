package DesignPatterns.builder
import DesignPatterns.model.cars.{Car, Minivan}

class MinivanBuilder extends CarBuilder {
  private var weight: Option[Int] = None

  def setWeight(weight: Int): Unit = {
    this.weight = Some(weight)
  }

  override def getResult: Car = {
    if (!allBasicFieldsDefined || weight.isEmpty) {
      throw new NotAllFieldsDefinedException
    } else {
      new Minivan(engineType.get, qualityType.get, maxSpeed.get, weight.get)
    }
  }
}
