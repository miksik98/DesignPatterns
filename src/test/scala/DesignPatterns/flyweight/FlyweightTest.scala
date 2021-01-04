package DesignPatterns.flyweight

import DesignPatterns.BasicTest

class FlyweightTest extends BasicTest {
  test("kabriolet manual") {
    assert(ManualFactory.createKabrioletManual.equals(ManualFactory.createKabrioletManual))
  }

  test("kombi manual") {
    assert(ManualFactory.createKombiManual.equals(ManualFactory.createKombiManual))
  }

  test("minivan manual") {
    assert(ManualFactory.createMinivanManual.equals(ManualFactory.createMinivanManual))
  }

  test("sedan manual") {
    assert(ManualFactory.createSedanManual.equals(ManualFactory.createSedanManual))
  }
}
