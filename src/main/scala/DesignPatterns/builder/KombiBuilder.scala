package DesignPatterns.builder
import DesignPatterns.model.{Car, Kombi}

class KombiBuilder extends CarBuilder {
  private var bootCapacity: Option[Int] = None

  def setBootCapacity(bootCapacity: Int): Unit = {
    this.bootCapacity = Some(bootCapacity)
  }

  override def getResult: Car = {
    if (!allBasicFieldsDefined || bootCapacity.isEmpty){
      throw new NotAllFieldsDefinedException
    } else {
      new Kombi(engineType.get, qualityType.get, maxSpeed.get, bootCapacity.get)
    }
  }
}
