package DesignPatterns.decorator

class ReverseTuningDecorator(tuningable: Tuningable) extends BaseTuningDecorator(tuningable) {
  override def getTuningSpeed: Int = {
    super.getTuningSpeed - 20
  }
}
