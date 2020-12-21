package DesignPatterns.builder
import DesignPatterns.model.cars.{Car, Sedan}

class SedanBuilder extends CarBuilder {
  private var doorsNumber: Option[Int] = None

  def setDoorsNumber(doorsNumber: Int): Unit = {
    this.doorsNumber = Some(doorsNumber)
  }

  override def getResult: Car = {
    if (!allBasicFieldsDefined || doorsNumber.isEmpty) {
      throw new NotAllFieldsDefinedException
    } else {
      new Sedan(engineType.get, qualityType.get, maxSpeed.get, doorsNumber.get)
    }
  }
}
