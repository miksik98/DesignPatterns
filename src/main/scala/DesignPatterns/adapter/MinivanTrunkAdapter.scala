package DesignPatterns.adapter

import DesignPatterns.model.TrunkCompatibility
import DesignPatterns.model.cars.{Kombi, Minivan}

class MinivanTrunkAdapter(minivan: Minivan) extends TrunkCompatibility {
  override def getTrunkCapacity: Int = minivan.getCapacityFromRemovedSeats
}
