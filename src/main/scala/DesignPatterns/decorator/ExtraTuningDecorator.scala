package DesignPatterns.decorator

class ExtraTuningDecorator(tuningable: Tuningable) extends BaseTuningDecorator(tuningable) {
  override def getTuningSpeed: Int = {
    super.getTuningSpeed + 50
  }
}
