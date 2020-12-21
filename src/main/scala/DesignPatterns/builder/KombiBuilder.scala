package DesignPatterns.builder
import DesignPatterns.model.cars.{Car, Kombi}

class KombiBuilder extends CarBuilder {
  private var trunkCapacity: Option[Int] = None

  def setTrunkCapacity(trunkCapacity: Int): Unit = {
    this.trunkCapacity = Some(trunkCapacity)
  }

  override def getResult: Car = {
    if (!allBasicFieldsDefined || trunkCapacity.isEmpty){
      throw new NotAllFieldsDefinedException
    } else {
      new Kombi(engineType.get, qualityType.get, maxSpeed.get, trunkCapacity.get)
    }
  }
}
