package DesignPatterns.model.manuals

class KombiManual extends BasicManual {
  override val manualParts: Seq[String] = basicParts ++ Seq(PartsNames.TRUNK_INFO)

  override def toString: String = {
    "Kombi manual " + serialNumber
  }
}
