package DesignPatterns.adapter

import DesignPatterns.model.TrunkCompatibility
import DesignPatterns.model.cars.Sedan

class SedanTrunkAdapter(sedan: Sedan) extends TrunkCompatibility {
  override def getTrunkCapacity: Int = sedan.getCapacityFromRemovedSeats
}
