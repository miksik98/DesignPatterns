package DesignPatterns.decorator

abstract class BaseTuningDecorator(tuningable: Tuningable) extends Tuningable {
  override def getTuningSpeed: Int = {
    tuningable.getTuningSpeed
  }

  override var maxSpeed: Int = tuningable.maxSpeed

  override def getMaxSpeed: Int = maxSpeed
}
