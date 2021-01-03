package DesignPatterns.observer

import DesignPatterns.model.cars.{Car, QualityType}
import DesignPatterns.strategy.{ImprovingStrategy, SimpleImproveStrategy}

class CarQualityImprover extends Publisher[Car] {
  var subscribers: Seq[Car] = Seq.empty

  def addSubscriber(car: Car): Unit = {
    subscribers = subscribers :+ car
  }

  def removeSubscriber(car: Car): Unit = {
    subscribers = subscribers.filterNot(c => c == car)
  }

  def notifySubscribers(improvingStrategy: ImprovingStrategy = SimpleImproveStrategy): Unit = {
    subscribers.foreach(_.update(improvingStrategy))
    subscribers = subscribers.filterNot(_.getQualityType == QualityType.High)
  }
}
