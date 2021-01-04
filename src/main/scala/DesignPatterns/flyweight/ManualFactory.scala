package DesignPatterns.flyweight

import DesignPatterns.model.manuals.{BasicManual, KabrioletManual, KombiManual, MinivanManual, SedanManual}

object ManualFactory {
  private lazy val cachedKabrioletManual = new KabrioletManual
  private lazy val cachedKombiManual = new KombiManual
  private lazy val cachedMinivanManual = new MinivanManual
  private lazy val cachedSedanManual = new SedanManual

  def createKabrioletManual: KabrioletManual = cachedKabrioletManual
  def createKombiManual: KombiManual = cachedKombiManual
  def createMinivanManual: MinivanManual = cachedMinivanManual
  def createSedanManual: SedanManual = cachedSedanManual
}
