package DesignPatterns.observer

import DesignPatterns.strategy.ImprovingStrategy

trait Subscriber {
  def update(improvingStrategy: ImprovingStrategy): Unit
}