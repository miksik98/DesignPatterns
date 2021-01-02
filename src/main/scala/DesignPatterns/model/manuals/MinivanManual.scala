package DesignPatterns.model.manuals

class MinivanManual extends BasicManual {
  override val manualParts: Seq[String] = basicParts ++ Seq(PartsNames.WEIGHT_INFO)

  override def toString: String = {
    "Minivan manual " + serialNumber
  }
}
