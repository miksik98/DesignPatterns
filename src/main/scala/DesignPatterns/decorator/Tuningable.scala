package DesignPatterns.decorator

trait Tuningable {
  def getTuningSpeed: Int

  def engineTuning(): Unit = {
    maxSpeed = maxSpeed + getTuningSpeed
  }

  var maxSpeed: Int

  def getMaxSpeed: Int
}
