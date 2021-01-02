package DesignPatterns.memento

import scala.collection.mutable

object CarProducerHistory {
  private val savedSnapshots: mutable.Stack[CarProducerSnapshot] = mutable.Stack.empty

  def makeBackup(snapshot: CarProducerSnapshot): Unit = {
    savedSnapshots.push(snapshot)
  }

  def removeLastSaved(): Unit = {
    savedSnapshots.pop()
  }

  def restore(): Unit = {
    if (savedSnapshots.isEmpty) {
      throw new EmptyHistoryException("NO SAVED STATES!")
    } else {
      savedSnapshots.pop().restore()
    }
  }
}

class EmptyHistoryException(val message: String) extends RuntimeException
