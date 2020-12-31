package DesignPatterns.observer

trait Subscriber {
  def update(): Unit
}