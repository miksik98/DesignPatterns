package DesignPatterns.iterator

trait Iterator[T] {
  def next: T
  def hasNext: Boolean
}

trait IterableCollection[T] {
  def createIterator(): Iterator[T]
}