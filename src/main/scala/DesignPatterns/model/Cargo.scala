package DesignPatterns.model

class Cargo(capacity: Int) {
  def fitsTo(trunkCompatibility: TrunkCompatibility): Boolean = {
    trunkCompatibility.getTrunkCapacity >= capacity
  }
}
