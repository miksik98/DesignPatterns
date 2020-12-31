package DesignPatterns.composite

import DesignPatterns.iterator.{IterableCollection, Iterator, ProductionFlowComponentIterator}
import DesignPatterns.model.cars.Car
import DesignPatterns.visitor.{ProductionFlowVisitable, ProductionFlowVisitor}

import scala.collection.mutable
import scala.util.Random

trait ProductionFlowComponent extends ProductionFlowVisitable[Int] {
  def getCars: Seq[Car]
}

class SubContractor(var components: Seq[ProductionFlowComponent],
                    val name: String = new Random().nextString(5),
                    val isMainContractor: Boolean = false)
  extends ProductionFlowComponent with IterableCollection[ProductionFlowComponent] {

  if (!isMainContractor && components.isEmpty) {
    throw new IllegalArgumentException("Components could not be empty")
  }

  def getComponents: Seq[ProductionFlowComponent] = components

  def removeComponent(component: ProductionFlowComponent): Unit = this.synchronized {
    components = components.filterNot(c => c == component)
  }

  def addComponent(component: ProductionFlowComponent): Unit = this.synchronized {
    components = components :+ component
  }

  def addCar(car: Car): Unit = this.synchronized {
    components = components :+ FinalProduct(car, this)
  }

  def reset(): Unit = this.synchronized {
    components = Seq.empty
  }

  def getCars: Seq[Car] = {
    getComponents.map(sc => sc.getCars) match {
      case Nil => Seq.empty
      case c :: Nil => c
      case c => c.reduce(_ ++ _)
    }
  }

  override def toString: String = {
    "subcontractor " + name
  }

  override def createIterator(): Iterator[ProductionFlowComponent] = {
    new ProductionFlowComponentIterator(this)
  }

  def printTree(): Unit = {
    val it = this.createIterator()
    val stack: mutable.Stack[(Int, Int)] = mutable.Stack.empty
    var indentations = 0
    while (it.hasNext) {
      val x = it.next
      val str = {
        if (indentations > 0) {
          Range(0, indentations).map(_ => "\t").reduce(_ + _)
        } else {
          ""
        }
      }
      println(str + x.toString)
      x match {
        case sc: SubContractor =>
          stack.push((sc.getComponents.length, indentations))
          indentations = indentations + 1
        case _: FinalProduct if it.hasNext =>
          var tuple = stack.pop()
          while (tuple._1 == 1) {
            tuple = stack.pop()
          }
          indentations = tuple._2 + 1
          stack.push((tuple._1 - 1, tuple._2))
        case _ =>
      }
    }
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case other: SubContractor =>
        this.name == other.name
      case _ => false
    }
  }

  override def accept(visitor: ProductionFlowVisitor[Int]): Int = {
    visitor.visit(this)
  }
}

class FinalProduct(val car: Car, var subContractor: Option[SubContractor]) extends ProductionFlowComponent {
  override def getCars: Seq[Car] = Seq(car)

  override def toString: String = car.toString

  def hasSerialNumber(serialNumber: Int): Boolean = {
    car.serialNumber == serialNumber
  }

  def removeFromSubContractor(): Unit = {
    subContractor match {
      case Some(c) => c.removeComponent(this)
      case None => sys.error("parent not set for final product " + this)
    }
  }

  def setSubContractor(subContractor: SubContractor): Unit = {
    this.subContractor = Some(subContractor)
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case other: FinalProduct =>
        this.car == other.car
      case _ => false
    }
  }

  override def accept(visitor: ProductionFlowVisitor[Int]): Int = {
    visitor.visit(this)
  }
}

object FinalProduct {
  def apply(car: Car, subContractor: SubContractor): FinalProduct = new FinalProduct(car, Some(subContractor))
  def apply(car: Car): FinalProduct = new FinalProduct(car, None)
}
