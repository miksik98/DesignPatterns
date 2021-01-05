package DesignPatterns.chain

import DesignPatterns.chain.FilterName.FilterName

object FilterName extends Enumeration {
  type FilterName = Value
  val Kombi, Faulty, Speed = Value
}

class FiltersMap {
  private var values: Map[FilterName, Boolean] = FilterName.values.map(v => v -> false).toMap

  def setFilter(name: FilterName, value: Boolean = true): Unit = {
    values = values.map(el => {
      if (el._1 == name) {
        (name, value)
      } else {
        el
      }
    })
  }

  def get(name: FilterName): Boolean = values(name)
}

class FilterRequest(val filtersMap: FiltersMap)

