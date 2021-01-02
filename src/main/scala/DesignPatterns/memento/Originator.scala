package DesignPatterns.memento

trait Originator {
  def save(): Memento
}
