package DesignPatterns.state

trait CarProducerState {
  def deleteCar(serialNumber: Int): Unit
}
