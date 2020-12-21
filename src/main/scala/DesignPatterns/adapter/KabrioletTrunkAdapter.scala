package DesignPatterns.adapter

import DesignPatterns.model.TrunkCompatibility
import DesignPatterns.model.cars.Kabriolet

class KabrioletTrunkAdapter(kabriolet: Kabriolet) extends TrunkCompatibility {
  override def getTrunkCapacity: Int = kabriolet.getCapacityFromRemovedSeats
}
