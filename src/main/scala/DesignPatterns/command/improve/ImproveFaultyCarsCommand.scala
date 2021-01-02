package DesignPatterns.command.improve

import DesignPatterns.command.Command
import DesignPatterns.facade.ImproveOperationHandler
import DesignPatterns.model.cars.QualityType.QualityType

class ImproveFaultyCarsCommand(implicit operationHandler: ImproveOperationHandler) extends Command {
  private var previousCars: Option[Seq[(Int, QualityType)]] = None
  private var currentCars: Option[Seq[(Int, QualityType)]] = None

  def getCommonSerialNumbers(prevCars: Seq[(Int, QualityType)], curCars: Seq[(Int, QualityType)]): Seq[Int] = {
    if (prevCars.size != curCars.size) {
      sys.error("improving cars changed size of produced cars")
    }
    var result: Seq[Int] = Seq.empty
    for (i <- prevCars.indices) {
      if (prevCars(i)._2 == curCars(i)._2) {
        result = result :+ prevCars(i)._1
      }
    }
    result
  }

  override def execute(): Unit = {
    val prevCars = operationHandler.getCreatedCars.map(c => Tuple2(c.serialNumber, c.qualityType))
    operationHandler.improveFaultyCars()
    val curCars = operationHandler.getCreatedCars.map(c => Tuple2(c.serialNumber, c.qualityType))
    val notChanged = getCommonSerialNumbers(prevCars, curCars)
    previousCars = Some(prevCars.filterNot(x => notChanged.contains(x._1)))
    currentCars = Some(curCars.filterNot(x => notChanged.contains(x._1)))
  }

  override def undo(): Unit = {
    operationHandler.getCreatedCars.foreach(
      {
        car =>
          val prevOpt = previousCars.get.find(c => c._1 == car.serialNumber)
          if (prevOpt.isDefined) {
            car.setQualityType(prevOpt.get._2)
          }
      }
    )
  }

  override def redo(): Unit = {
    operationHandler.getCreatedCars.foreach(
      {
        car =>
          val prevOpt = currentCars.get.find(c => c._1 == car.serialNumber)
          if (prevOpt.isDefined) {
            car.setQualityType(prevOpt.get._2)
          }
      }
    )
  }

  override def getName: String = "Improving quality of faulty cars"
}
