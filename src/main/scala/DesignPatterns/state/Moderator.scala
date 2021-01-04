package DesignPatterns.state

import DesignPatterns.singleton.InsufficientPrivileges

object Moderator extends CarProducerState {
  override def deleteCar(serialNumber: Int): Unit = {
    throw new InsufficientPrivileges
  }
}
