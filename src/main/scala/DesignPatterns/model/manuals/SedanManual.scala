package DesignPatterns.model.manuals

class SedanManual extends BasicManual {
  override val manualParts: Seq[String] = basicParts ++ Seq(PartsNames.DOORS_INFO)

  override def toString: String = {
    "Sedan manual " + serialNumber
  }
}
