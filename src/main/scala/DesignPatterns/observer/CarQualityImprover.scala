package DesignPatterns.observer

import DesignPatterns.model.cars.{Car, QualityType}

class CarQualityImprover extends Publisher[Car] {
  var subscribers: Seq[Car] = Seq.empty

  def addSubscriber(car: Car): Unit = {
    subscribers = subscribers :+ car
  }

  def removeSubscriber(car: Car): Unit = {
    subscribers = subscribers.filterNot(c => c == car)
  }

  def notifySubscribers(): Unit = {
    subscribers.foreach(_.update())
    subscribers = subscribers.filterNot(_.getQualityType == QualityType.High)
  }
}
