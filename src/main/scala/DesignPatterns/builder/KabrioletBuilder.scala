package DesignPatterns.builder
import DesignPatterns.model.{Car, Kabriolet}

class KabrioletBuilder extends CarBuilder {
  private var hasRoof: Option[Boolean] = None

  def addRoof(): Unit = {
    hasRoof = Some(true)
  }

  def removeRoof(): Unit = {
    hasRoof = Some(false)
  }

  override def getResult: Car = {
    if (!allBasicFieldsDefined || hasRoof.isEmpty) {
      throw new NotAllFieldsDefinedException
    } else {
      new Kabriolet(engineType.get, qualityType.get, maxSpeed.get, hasRoof.get)
    }
  }
}
