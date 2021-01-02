package DesignPatterns.model.manuals

import DesignPatterns.factory.Generators

abstract class BasicManual {
  val serialNumber: Int = Generators.SerialNumberGenerator.generate()
  var basicParts: Seq[String] = Seq(PartsNames.SAFETY, PartsNames.TECH_INFO, PartsNames.USAGE)
  val manualParts: Seq[String]

  def getTableOfContents: String = {
    manualParts.zipWithIndex.map(el => el._2 + ". " + el._1).mkString("\n")
  }
}
