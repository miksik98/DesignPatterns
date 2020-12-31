package DesignPatterns.observer

trait Publisher[T <: Subscriber] {
  var subscribers: Seq[T]
  def addSubscriber(subscriber: T): Unit
  def removeSubscriber(subscriber: T): Unit
  def notifySubscribers(): Unit
}
