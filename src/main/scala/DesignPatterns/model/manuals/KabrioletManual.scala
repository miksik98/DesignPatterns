package DesignPatterns.model.manuals

class KabrioletManual extends BasicManual {
  override val manualParts: Seq[String] = basicParts ++ Seq(PartsNames.ROOF_INFO)

  override def toString: String = {
    "Kabriolet manual " + serialNumber
  }
}
