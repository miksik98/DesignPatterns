package DesignPatterns.observer

import DesignPatterns.strategy.ImprovingStrategy

trait Publisher[T <: Subscriber] {
  var subscribers: Seq[T]
  def addSubscriber(subscriber: T): Unit
  def removeSubscriber(subscriber: T): Unit
  def notifySubscribers(improvingStrategy: ImprovingStrategy): Unit
}
