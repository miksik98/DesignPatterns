package DesignPatterns.visitor
import DesignPatterns.composite.{FinalProduct, ProductionFlowComponent, SubContractor}
import DesignPatterns.model.cars.{Car, Kabriolet, Kombi, Minivan, Sedan}

object CostProductionFlowVisitor extends ProductionFlowVisitor[Int] {
  private val SUBCONTRACTOR_SALARY = 20000
  private val QUALITY_MAX_COST = 50000
  private val DIESEL_COST = 4000
  private val LPG_COST = 3000
  private val KABRIOLET_ROOF_COST = 5000

  override def visit(productionFlowComponent: ProductionFlowComponent): Int = {
    productionFlowComponent match {
      case product: FinalProduct => visit(product)
      case contractor: SubContractor => visit(contractor)
      case _ => sys.error("unknown production flow component " + productionFlowComponent)
    }
  }

  private def visit(subContractor: SubContractor): Int = {
    if (subContractor.isMainContractor) {
      0
    } else {
      subContractor.getComponents.size * SUBCONTRACTOR_SALARY
    }
  }

  private def visit(finalProduct: FinalProduct): Int = {
    calculateBasicCosts(finalProduct.car) + {
      finalProduct.car match {
        case kabriolet: Kabriolet => visitKabriolet(kabriolet)
        case kombi: Kombi => visitKombi(kombi)
        case minivan: Minivan => visitMinivan(minivan)
        case sedan: Sedan => visitSedan(sedan)
        case _ => sys.error("unknown car type " + finalProduct.car.carType)
      }
    }
  }

  private def visitKabriolet(kabriolet: Kabriolet): Int = {
    if (kabriolet.hasRoof) {
      KABRIOLET_ROOF_COST
    } else {
      (0.5 * KABRIOLET_ROOF_COST).toInt
    }
  }

  private def visitKombi(kombi: Kombi): Int = {
    kombi.trunkCapacity * 50
  }

  private def visitMinivan(minivan: Minivan): Int = {
    minivan.weight * 100
  }

  private def visitSedan(sedan: Sedan): Int = {
    sedan.doorsNumber * 500
  }

  private def calculateBasicCosts(car: Car): Int = {
    val qtCost = car.qualityType match {
      case DesignPatterns.model.cars.QualityType.Low => (0.5 * QUALITY_MAX_COST).toInt
      case DesignPatterns.model.cars.QualityType.Medium => (0.8 * QUALITY_MAX_COST).toInt
      case DesignPatterns.model.cars.QualityType.High => QUALITY_MAX_COST
    }

    val etCost = car.engineType match {
      case DesignPatterns.model.cars.EngineType.Diesel => DIESEL_COST
      case DesignPatterns.model.cars.EngineType.LPG => LPG_COST
    }

    qtCost + etCost + car.maxSpeed * 100 + car.maxSeatsNumber * 1000
  }
}
